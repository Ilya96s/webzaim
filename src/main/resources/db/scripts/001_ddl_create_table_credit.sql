CREATE TABLE IF NOT EXISTS credit (
    id SERIAL PRIMARY KEY ,
    payment_string VARCHAR NOT NULL ,
    payment_date DATE NOT NULL
);

comment on table credit is 'Таблица с кредитами';
comment on column credit.id is 'Идентификатор записи';
comment on column credit.payment_string is 'Строка с кодами платежей';
comment on column credit.payment_date is 'Дата первого платежа';