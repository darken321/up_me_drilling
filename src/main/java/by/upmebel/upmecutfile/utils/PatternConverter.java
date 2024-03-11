package by.upmebel.upmecutfile.utils;

import by.upmebel.upmecutfile.projection.PartSize;
import dto.SizePattern;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;


/**
 * Класс предназначен для подсчета координат отверстия с учетом размеров деталей
 * с использованием заданных математических паттернов. Позволяет выполнять базовые
 * математические операции над размерами деталей, L, B, H определенными в интерфейсе
 * {@link PartSize}, согласно паттернам, указанным в списке {@link SizePattern}.
 *
 * <p>Преобразование выполняется методом {@code convert}, который принимает список паттернов и объект, содержащий размеры детали.
 * Каждый паттерн в списке определяет операцию, которая должна быть применена к одному из размеров детали (длина, ширина, высота),
 * и значение, которое используется в этой операции. Результатом является сумма всех примененных операций.</p>
 *
 */
@Component
@Validated
@RequiredArgsConstructor
public class PatternConverter {

    @FunctionalInterface
    interface Operation {
        double apply(double variable, double value);
    }

    /**
     * Конвертер для расчета координаты отверстия из паттерна. Каждый паттерн в списке
     * определяет какой размер детали учитывать (длина, ширина, высота),
     * операцию, которая должна быть применена к одному из размеров детали (+, -, *, /)
     * и цифровое значение, которое используется в этой операции.
     * Результатом является сумма всех примененных операций
     * @param patterns  список паттернов
     * @param partSizes  размеры детали
     * @return координата как результат конвертации
     */
    public Double convert(@Valid List<SizePattern> patterns, @Valid PartSize partSizes) {
        double result = 0.0;

        Map<String, Operation> operations = Map.of(
                "+", (variable, value) -> variable + value,
                "-", (variable, value) -> variable - value,
                "*", (variable, value) -> variable * value,
                "/", (variable, value) -> {
                    if (value == 0) {
                        throw new ArithmeticException("Деление на ноль");
                    }
                    return variable / value;
                }
        );

        for (SizePattern pattern : patterns) {
            Operation operation = operations.getOrDefault(pattern.getOperator(), (variable, value) -> variable);
            switch (pattern.getVariable()) {
                case "L" -> result += operation.apply(partSizes.getL(), pattern.getValue());
                case "B" -> result += operation.apply(partSizes.getB(), pattern.getValue());
                case "H" -> result += operation.apply(partSizes.getH(), pattern.getValue());
                case "" -> result += pattern.getValue();
            }
        }

        return result;
    }
}