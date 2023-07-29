package ru.test.webzaim.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ilya Kaltygin
 */
@Getter
@AllArgsConstructor
public enum CustomSymbol {

    ZERO('0', 0),

    ONE('1', 0),

    A('A', 2),

    TWO('2', 3),

    THREE('3', 4);

    private final Character symbol;

    private final int order;
}
