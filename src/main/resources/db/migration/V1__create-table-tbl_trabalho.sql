CREATE TABLE fluxo_trafego (
    data_hora               DATE NOT NULL,
    id_fluxo                NUMBER NOT NULL,
    volume                  NUMBER NOT NULL,
    sentido                 NUMBER NOT NULL,
    tb_semaforo_id_semaforo NUMBER NOT NULL
);

ALTER TABLE fluxo_trafego ADD CONSTRAINT fluxo_trafego_pk PRIMARY KEY (id_fluxo);

CREATE TABLE historico_ajustes (
    id_ajuste               NUMBER NOT NULL,
    data_hora               DATE NOT NULL,
    data_hora_ajuste        DATE NOT NULL,
    motivo_ajuste           CLOB NOT NULL,
    novo_tempo_verde        VARCHAR2(10) NOT NULL,
    novo_tempo_vermelho     VARCHAR2(10) NOT NULL,
    novo_tempo_amarelo      VARCHAR2(10) NOT NULL,
    tb_semaforo_id_semaforo NUMBER NOT NULL
);

ALTER TABLE historico_ajustes ADD CONSTRAINT historico_ajustes_pk PRIMARY KEY (id_ajuste);

CREATE TABLE tb_condicao_climatica (
    id_condicao             NUMBER NOT NULL,
    data_hora               DATE NOT NULL,
    temperatura             NUMBER NOT NULL,
    umidade                 NUMBER NOT NULL,
    condicao                VARCHAR2(50) NOT NULL,
    tb_semaforo_id_semaforo NUMBER NOT NULL
);

ALTER TABLE tb_condicao_climatica ADD CONSTRAINT tb_condicao_climatica_pk PRIMARY KEY (id_condicao);

CREATE TABLE tb_semaforo (
    id_semaforo             NUMBER NOT NULL,
    localidade              BLOB NOT NULL,
    atualizacao             DATE NOT NULL,
    status                  VARCHAR2(20) NOT NULL
);

ALTER TABLE tb_semaforo ADD CONSTRAINT tb_semaforo_pk PRIMARY KEY (id_semaforo);

CREATE TABLE tempo_sinal (
    id_tempo                NUMBER NOT NULL,
    tempo_verde             VARCHAR2(10) NOT NULL,
    tempo_amarelo           VARCHAR2(10) NOT NULL,
    tempo_vermelho          VARCHAR2(10) NOT NULL,
    tb_semaforo_id_semaforo NUMBER NOT NULL
);

ALTER TABLE tempo_sinal ADD CONSTRAINT tempo_sinal_pk PRIMARY KEY (id_tempo);
ALTER TABLE fluxo_trafego
    ADD CONSTRAINT fluxo_tb_semaforo_fk FOREIGN KEY (tb_semaforo_id_semaforo)
        REFERENCES tb_semaforo (id_semaforo);

ALTER TABLE tb_condicao_climatica
    ADD CONSTRAINT cond_clima_tb_semaforo_fk FOREIGN KEY (tb_semaforo_id_semaforo)
        REFERENCES tb_semaforo (id_semaforo);

ALTER TABLE historico_ajustes
    ADD CONSTRAINT hist_ajustes_tb_semaforo_fk FOREIGN KEY (tb_semaforo_id_semaforo)
        REFERENCES tb_semaforo (id_semaforo);

ALTER TABLE tempo_sinal
    ADD CONSTRAINT tempo_tb_semaforo_fk FOREIGN KEY (tb_semaforo_id_semaforo)
        REFERENCES tb_semaforo (id_semaforo);


CREATE OR REPLACE PROCEDURE Ajuste_Tempo_Sinal_Fluxo_Trafego (p_ID_Semaforo NUMBER) IS
  v_volume NUMBER;
  v_tempo_verde NUMBER := 30;
BEGIN
  SELECT volume INTO v_volume
  FROM fluxo_trafego
  WHERE tb_semaforo_id_semaforo = p_ID_Semaforo AND data_hora = TRUNC(SYSDATE);

  IF v_volume > 100 THEN
    v_tempo_verde := v_tempo_verde + 10;
  ELSIF v_volume < 30 THEN
    v_tempo_verde := v_tempo_verde - 5;
  END IF;

  UPDATE tempo_sinal
  SET tempo_verde = v_tempo_verde
  WHERE tb_semaforo_id_semaforo = p_ID_Semaforo;

  COMMIT;
END Ajuste_Tempo_Sinal_Fluxo_Trafego;
/


CREATE OR REPLACE PROCEDURE Ajuste_Tempo_Sinal_Clima (p_ID_Semaforo NUMBER) IS
  v_condicao VARCHAR2(50);
  v_tempo_verde NUMBER := 30;
BEGIN
  SELECT condicao INTO v_condicao
  FROM tb_condicao_climatica
  WHERE tb_semaforo_id_semaforo = p_ID_Semaforo AND data_hora = TRUNC(SYSDATE);

  IF v_condicao = 'Chuva' OR v_condicao = 'Neve' THEN
    v_tempo_verde := v_tempo_verde + 15;
  END IF;

  UPDATE tempo_sinal
  SET tempo_verde = v_tempo_verde
  WHERE tb_semaforo_id_semaforo = p_ID_Semaforo;

  COMMIT;
END Ajuste_Tempo_Sinal_Clima;
/
CREATE OR REPLACE PROCEDURE Prioriza_Veiculo_Emergencia (p_ID_Semaforo NUMBER, p_Veiculo_Emergencia BOOLEAN) IS
BEGIN
  IF p_Veiculo_Emergencia THEN
    UPDATE tempo_sinal
    SET tempo_verde = 60
    WHERE tb_semaforo_id_semaforo = p_ID_Semaforo;

    INSERT INTO historico_ajustes (id_ajuste, tb_semaforo_id_semaforo, data_hora_ajuste, motivo_ajuste)
    VALUES (seq_ajuste.NEXTVAL, p_ID_Semaforo, SYSDATE, 'Veículo de Emergência');

    COMMIT;
  END IF;
END Prioriza_Veiculo_Emergencia;
/
CREATE OR REPLACE PROCEDURE Detecta_Anomalia_Manutencao (p_ID_Semaforo NUMBER) IS
  v_status VARCHAR2(20);
  v_ultima_atualizacao DATE;
BEGIN
  SELECT status, atualizacao INTO v_status, v_ultima_atualizacao
  FROM tb_semaforo WHERE id_semaforo = p_ID_Semaforo;

  IF (SYSDATE - v_ultima_atualizacao) * 24 * 60 > 10 THEN
    INSERT INTO historico_ajustes (id_ajuste, tb_semaforo_id_semaforo, data_hora_ajuste, motivo_ajuste)
    VALUES (seq_ajuste.NEXTVAL, p_ID_Semaforo, SYSDATE, 'Falha detectada: Semáforo inativo por mais de 10 minutos');

    DBMS_OUTPUT.PUT_LINE('Alerta: Falha detectada no semáforo ' || p_ID_Semaforo);
  END IF;

  COMMIT;
END Detecta_Anomalia_Manutencao;
/
