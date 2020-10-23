package com.medeirosdaniel.XSSM.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("beanMapperCore")
public class BeanMapper {

    private ModelMapper modelMapper = new ModelMapper();

    /**
     * Mapeia os valores do objeto de origem para um novo objeto do tipo da classe informada.
     *
     * @param source Instancia do objeto de origem
     * @param targetClass Classe do objeto a ser criado e preenchido com os valor do objeto de origem
     * @return
     */
    public <T> T map(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T mappedObject = createNewInstance(targetClass);
        modelMapper.map(source, mappedObject);

        return mappedObject;
    }

    /**
     * Mapeia uma lista de objetos de origem em uma lista de objetos de destino
     *
     * @param sourceList Lista com os objetos de origem
     * @param targetClass Classe dos objetos a serem preenchidos com os valores de origem
     * @param <S> source Class
     * @param <D> destination Class
     * @return Lista de objetos do tipo destino
     */
    public <S, D> List<D> mapList(List<S> sourceList, Class<D> targetClass) {
        if (sourceList == null) {
            return Collections.emptyList();
        }

        List<D> dtoList = new ArrayList<>();

        for (S s : sourceList) {
            D dto = createNewInstance(targetClass);
            modelMapper.map(s, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Set list
     *
     * @param sourceList
     * @param targetClass
     * @return
     */
    public <S, D> Set<D> mapSet(Set<S> sourceList, Class<D> targetClass) {
        if (sourceList == null) {
            return Collections.emptySet();
        }

        Set<D> dtoList = new HashSet<>();

        for (S s : sourceList) {
            D dto = createNewInstance(targetClass);
            modelMapper.map(s, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private <T> T createNewInstance(Class<T> targetClass) {
        try {
            return targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(
                    String.format(
                            "Falha ao criar uma instancia da classe %s", targetClass.getCanonicalName()),
                    e);
        }
    }
}
