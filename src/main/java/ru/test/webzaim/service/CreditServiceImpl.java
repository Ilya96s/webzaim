package ru.test.webzaim.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.test.webzaim.dto.CreditDTO;
import ru.test.webzaim.enums.CustomSymbol;
import ru.test.webzaim.mapper.CreditMapper;
import ru.test.webzaim.repository.CreditRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * CreditServiceImpl - реализация сериа по работе с кредитами
 *
 * @author Ilya Kaltygin
 */
@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {

    /**
     * Хранилище кредитов
     */
    private final CreditRepository creditRepository;

    /**
     * Маппер
     */
    private final CreditMapper creditMapper;


    /**
     * Рассчитывает сквозную строку платежей по данным из таблицы кредитов
     *
     * @return сквозная трока платежей
     */
    @Override
    public String getThroughString() {
        var credits = creditRepository.findAll();
        if (credits.isEmpty()) {
            return "Нет данных";
        }
        var creditList = creditMapper.toCreditDtoList(credits);
        TreeMap<LocalDate, Character> map = new TreeMap<>();
        for (CreditDTO credit : creditList) {
            for (int i = 0; i < credit.getCodes().size(); i++) {
                int finalInt = i;
                map.computeIfAbsent(credit.getDates().get(i), key -> credit.getCodes().get(finalInt));
                map.computeIfPresent(credit.getDates().get(i), (key, value) -> {
                    return value = compareChar(value, credit.getCodes().get(finalInt));
                });
            }
        }
        var localDateCharacterTreeMap = fillEmptyDate(map);
        StringBuilder sb = new StringBuilder();
        for (LocalDate date : localDateCharacterTreeMap.keySet()) {
            sb.append(date).append(" ").append(localDateCharacterTreeMap.get(date)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Сравнивает 2 символа между собой
     *
     * @param currentChar текущий объект
     * @param nextChar    новый объект
     */
    private Character compareChar(Character currentChar, Character nextChar) {
        var values = CustomSymbol.values();
        var currentSymbol = Arrays.stream(values)
                .filter(v -> v.getSymbol().equals(currentChar))
                .findFirst().get();
        var nextSymbol = Arrays.stream(values)
                .filter(v -> v.getSymbol().equals(nextChar))
                .findFirst().get();
        Character result;
        if (currentSymbol.getOrder() > nextSymbol.getOrder()) {
            result = currentChar;
        } else if (currentSymbol.getOrder() < nextSymbol.getOrder()) {
            result = nextChar;
        } else {
            result = currentChar;
        }
        return result;
    }


    /**
     * Заполняет промежутки между датами и устанавливает для них код 'X'
     *
     * @param map структура даннызх, содержащая пары ключ(дата платежа) - значение (код платежа)
     * @return структура данных с добавленными датами
     */
    private TreeMap<LocalDate, Character> fillEmptyDate(TreeMap<LocalDate, Character> map) {
        var firstDate = map.firstEntry().getKey();
        var findDate = firstDate.plusMonths(1);
        var lastDate = map.lastEntry().getKey();
        while (!firstDate.equals(lastDate)) {
            map.putIfAbsent(firstDate, 'X');
            findDate = findDate.plusMonths(1);
            firstDate = firstDate.plusMonths(1);
        }
        return map;
    }
}
