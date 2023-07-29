package ru.test.webzaim.mapper;

import org.springframework.stereotype.Component;
import ru.test.webzaim.dto.CreditDTO;
import ru.test.webzaim.model.Credit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CreditMapper - объект, выполняющий отображение данных типа Credit на тип CreditDTO
 *
 * @author Ilya Kaltygin
 */
@Component
public class CreditMapper {


    /**
     * Преобразует объект типа Credit в creditDTO
     *
     * @return объект типа creditDTO
     */
    public CreditDTO toCreditDTO(Credit credit) {
        return CreditDTO.builder()
                .codes(getCodesFromString(credit.getPaymentString()))
                .dates(getAllDates(getCodesFromString(credit.getPaymentString()), credit))
                .build();
    }

    /**
     * Преобразует список объектов типа Credit в список объектов типа creditDTO
     *
     * @param creditList список объектов типа Credit
     * @return список объектов типа creditDTO
     */
    public List<CreditDTO> toCreditDtoList(List<Credit> creditList) {
        return creditList.stream()
                .map(this::toCreditDTO)
                .toList();
    }

    /**
     * Получает список всех кодов всех платежей
     * Переворачиваем список, так как платежи читаются справа налево
     *
     * @param paymentString строка, содержащая коды
     */
    private List<Character> getCodesFromString(String paymentString) {
        var characters = new ArrayList<>(paymentString.chars().mapToObj(i -> (char) i).toList());
        Collections.reverse(characters);
        return characters;
    }

    /**
     * Получает список дат для каждого платежа
     *
     * @param codes  список кодов нужен для того, чтобы создать необходимое количество дат
     * @param credit объект типа Credit
     * @return список дат всех платежей
     */
    private List<LocalDate> getAllDates(List<Character> codes, Credit credit) {
        List<LocalDate> dates = new ArrayList<>();
        var firstPaymentDate = credit.getPaymentDate();
        for (Character ch : codes) {
            dates.add(firstPaymentDate);
            firstPaymentDate = firstPaymentDate.plusMonths(1);
        }
        return dates;
    }
}
