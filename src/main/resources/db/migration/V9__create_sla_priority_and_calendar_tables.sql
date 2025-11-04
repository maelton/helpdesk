CREATE TABLE IF NOT EXISTS tb_sla_priority
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    level       INTEGER NOT NULL,
    color       VARCHAR(7),
    is_active   BOOLEAN NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT uq_tb_sla_priority_name UNIQUE (name),
    CONSTRAINT uq_tb_sla_priority_level UNIQUE (level),
    CONSTRAINT ck_tb_sla_priority_level_positive CHECK (level > 0)
);

CREATE TABLE IF NOT EXISTS tb_sla_calendar
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name                VARCHAR(255) NOT NULL,
    description         VARCHAR(500),
    timezone            VARCHAR(50) NOT NULL DEFAULT 'America/Sao_Paulo',
    consider_weekends   BOOLEAN NOT NULL DEFAULT FALSE,
    consider_holidays   BOOLEAN NOT NULL DEFAULT TRUE,
    is_active           BOOLEAN NOT NULL DEFAULT TRUE,
    created_at          TIMESTAMP WITHOUT TIME ZONE,
    updated_at          TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT uq_tb_sla_calendar_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS tb_sla_day
(
    id              BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    calendar_id     BIGINT NOT NULL,
    day_of_week     VARCHAR(9) NOT NULL,
    start_time      TIME NOT NULL,
    end_time        TIME NOT NULL,
    is_working_day  BOOLEAN NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT uq_tb_sla_day_calendar_day UNIQUE (calendar_id, day_of_week),
    CONSTRAINT ck_tb_sla_day_valid_day CHECK (day_of_week IN ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY')),
    CONSTRAINT ck_tb_sla_day_time_order CHECK (start_time < end_time)
);

CREATE TABLE IF NOT EXISTS tb_sla_exception
(
    id              BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name            VARCHAR(255) NOT NULL,
    description     VARCHAR(500),
    exception_date  DATE NOT NULL,
    type            VARCHAR(20) NOT NULL,
    start_time      TIME,
    end_time        TIME,
    is_recurring    BOOLEAN NOT NULL DEFAULT FALSE,
    is_active       BOOLEAN NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT ck_tb_sla_exception_type CHECK (type IN ('HOLIDAY', 'SPECIAL_HOURS', 'NON_WORKING_DAY')),
    CONSTRAINT ck_tb_sla_exception_special_hours CHECK (
        (type != 'SPECIAL_HOURS') OR 
        (type = 'SPECIAL_HOURS' AND start_time IS NOT NULL AND end_time IS NOT NULL AND start_time < end_time)
    )
);

CREATE TABLE IF NOT EXISTS tb_sla_calendar_exception
(
    calendar_id     BIGINT NOT NULL,
    exception_id    BIGINT NOT NULL,
    CONSTRAINT pk_tb_sla_calendar_exception PRIMARY KEY (calendar_id, exception_id)
);

ALTER TABLE tb_sla_day
    ADD CONSTRAINT fk_tb_sla_day_calendar FOREIGN KEY (calendar_id) REFERENCES tb_sla_calendar (id);

ALTER TABLE tb_sla_calendar_exception
    ADD CONSTRAINT fk_tb_sla_calendar_exception_calendar FOREIGN KEY (calendar_id) REFERENCES tb_sla_calendar (id);

ALTER TABLE tb_sla_calendar_exception
    ADD CONSTRAINT fk_tb_sla_calendar_exception_exception FOREIGN KEY (exception_id) REFERENCES tb_sla_exception (id);

ALTER TABLE tb_sla 
    ADD COLUMN IF NOT EXISTS calendar_id BIGINT,
    ADD COLUMN IF NOT EXISTS priority_id BIGINT;

ALTER TABLE tb_sla
    ADD CONSTRAINT fk_tb_sla_calendar FOREIGN KEY (calendar_id) REFERENCES tb_sla_calendar (id);

ALTER TABLE tb_sla
    ADD CONSTRAINT fk_tb_sla_priority FOREIGN KEY (priority_id) REFERENCES tb_sla_priority (id);

CREATE INDEX IF NOT EXISTS ix_tb_sla_priority_level ON tb_sla_priority(level);
CREATE INDEX IF NOT EXISTS ix_tb_sla_priority_is_active ON tb_sla_priority(is_active);
CREATE INDEX IF NOT EXISTS ix_tb_sla_calendar_is_active ON tb_sla_calendar(is_active);
CREATE INDEX IF NOT EXISTS ix_tb_sla_day_calendar_id ON tb_sla_day(calendar_id);
CREATE INDEX IF NOT EXISTS ix_tb_sla_day_day_of_week ON tb_sla_day(day_of_week);
CREATE INDEX IF NOT EXISTS ix_tb_sla_exception_date ON tb_sla_exception(exception_date);
CREATE INDEX IF NOT EXISTS ix_tb_sla_exception_is_active ON tb_sla_exception(is_active);
CREATE INDEX IF NOT EXISTS ix_tb_sla_exception_is_recurring ON tb_sla_exception(is_recurring);
CREATE INDEX IF NOT EXISTS ix_tb_sla_calendar_id ON tb_sla(calendar_id);
CREATE INDEX IF NOT EXISTS ix_tb_sla_priority_id ON tb_sla(priority_id);