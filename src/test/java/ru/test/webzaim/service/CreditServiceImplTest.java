package ru.test.webzaim.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.test.webzaim.dto.CreditDTO;
import ru.test.webzaim.mapper.CreditMapper;
import ru.test.webzaim.model.Credit;
import ru.test.webzaim.repository.CreditRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreditServiceImplTest {

    private static CreditServiceImpl creditService;

    private static CreditRepository creditRepository;

    private static CreditMapper creditMapper;

    @BeforeAll
    static void init() {
        creditRepository = mock(CreditRepository.class);
        creditMapper = mock(CreditMapper.class);
        creditService = new CreditServiceImpl(creditRepository, creditMapper);
    }

    @Test
    void whenGetCorrectString() {

        String payment1 = "111111";
        var date1 = LocalDate.of(2020, 1, 1);
        Credit credit1 = new Credit(1L, payment1, date1);

        String payment2 = "32A10";
        var date2 = LocalDate.of(2020, 3, 1);
        Credit credit2 = new Credit(2L, payment2, date2);

        String payment3 = "111";
        var date3 = LocalDate.of(2020, 10, 1);
        Credit credit3 = new Credit(3L, payment3, date3);

        List<Credit> credits = List.of(credit1, credit2, credit3);

        var creditDto1 = CreditDTO.builder()
                .codes(List.of('1', '1', '1', '1', '1', '1'))
                .dates(List.of(
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 2, 1),
                        LocalDate.of(2020, 3, 1),
                        LocalDate.of(2020, 4, 1),
                        LocalDate.of(2020, 5, 1),
                        LocalDate.of(2020, 6, 1)))
                .build();

        var creditDto2 = CreditDTO.builder()
                .codes(List.of('0', '1', 'A', '2', '3'))
                .dates(List.of(
                        LocalDate.of(2020, 3, 1),
                        LocalDate.of(2020, 4, 1),
                        LocalDate.of(2020, 5, 1),
                        LocalDate.of(2020, 6, 1),
                        LocalDate.of(2020, 7, 1)))
                .build();

        var creditDto3 = CreditDTO.builder()
                .codes(List.of('1', '1', '1'))
                .dates(List.of(
                        LocalDate.of(2020, 10, 1),
                        LocalDate.of(2020, 11, 1),
                        LocalDate.of(2020, 12, 1)))
                .build();

        List<CreditDTO> creditDTOS = List.of(creditDto1, creditDto2, creditDto3);

        Mockito.when(creditRepository.findAll()).thenReturn(credits);

        Mockito.when(creditMapper.toCreditDtoList(credits)).thenReturn(creditDTOS);

        var expected = new StringBuilder();
        expected.append("2020-01-01").append(" ").append("1").append(System.lineSeparator());
        expected.append("2020-02-01").append(" ").append("1").append(System.lineSeparator());
        expected.append("2020-03-01").append(" ").append("1").append(System.lineSeparator());
        expected.append("2020-04-01").append(" ").append("1").append(System.lineSeparator());
        expected.append("2020-05-01").append(" ").append("A").append(System.lineSeparator());
        expected.append("2020-06-01").append(" ").append("2").append(System.lineSeparator());
        expected.append("2020-07-01").append(" ").append("3").append(System.lineSeparator());
        expected.append("2020-08-01").append(" ").append("X").append(System.lineSeparator());
        expected.append("2020-09-01").append(" ").append("X").append(System.lineSeparator());
        expected.append("2020-10-01").append(" ").append("1").append(System.lineSeparator());
        expected.append("2020-11-01").append(" ").append("1").append(System.lineSeparator());
        expected.append("2020-12-01").append(" ").append("1").append(System.lineSeparator());

        var actual = creditService.getThroughString();

        assertThat(actual).isEqualTo(expected.toString());
    }

    @Test
    void whenDatabaseIsEmpty() {

        List<Credit> credits = List.of();

        List<CreditDTO> creditDTOS = List.of();

        Mockito.when(creditRepository.findAll()).thenReturn(credits);

        Mockito.when(creditMapper.toCreditDtoList(credits)).thenReturn(creditDTOS);

        var expected = "Нет данных";

        var actual = creditService.getThroughString();

        assertThat(actual).isEqualTo(expected);
    }
}