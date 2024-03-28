package by.upmebel.upmecutfile.config;

import by.upmebel.upmecutfile.dto.hole.HoleSaveDto;
import by.upmebel.upmecutfile.model.Hole;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Конфигурационный класс для настройки {@link ModelMapper}.
 * <p>
 * Этот класс отвечает за создание и настройку экземпляра {@link ModelMapper},
 * чтоб Spring его видел как бин
 * </p>
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        /**настраивает правила маппинга для сохранения Hole из HoleSaveDto
        потому что в HoleSaveDto нет HoleId и его нужно пропустить
        иначе modelMapper по умолчанию тащит его из partId
        */
         modelMapper.addMappings(new PropertyMap<HoleSaveDto, Hole>() {
            @Override
            protected void configure() {
                skip(destination.getHoleId());
            }
        });
        return modelMapper;
    }
}