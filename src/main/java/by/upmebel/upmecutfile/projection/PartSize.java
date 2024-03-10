package by.upmebel.upmecutfile.projection;


/**
 * Интерфейс определяет структуру для получения размеров детали.
 * Он позволяет извлекать только необходимые атрибуты размеров из сущности
 * Part, такие как длина (L), ширина (B) и высота (H).
 */
public interface PartSize {
    Double getL();
    Double getB();
    Double getH();
}

