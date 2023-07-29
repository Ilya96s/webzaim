package ru.test.webzaim.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * CreditDTO - объект, содержащий в себе список кодов для каждого платежа и даты всех платежей
 *
 * @author Ilya Kaltygin
 */
@Data
@Builder
public class CreditDTO {

    private List<Character> codes;

    private List<LocalDate> dates;
}
