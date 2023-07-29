package ru.test.webzaim.service;

import ru.test.webzaim.model.Credit;

import java.util.List;

/**
 * CreditService - интерфейс, описывающий бизнес-логику по работе с кредитами
 *
 * @author Ilya Kaltygin
 */
public interface CreditService {

    /**
     * Рассчитывает сквозную строку платежей по данным из таблицы кредитов
     *
     * @return сквозная трока платежей
     */
    String getThroughString();
}
