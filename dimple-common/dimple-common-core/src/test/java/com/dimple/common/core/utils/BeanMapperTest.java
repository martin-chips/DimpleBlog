package com.dimple.common.core.utils;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.beans.Person;
import com.dimple.common.core.utils.beans.PersonVO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * BeanUtilsTest
 *
 * @author Dimple
 * @date 12/26/2022 8:56 AM
 */
public class BeanMapperTest {
    private List<Person> getPersons() {
        return Arrays.asList(new Person("Tik", 12, Arrays.asList(), LocalDateTime.now()),
                new Person("Tik", 12, Arrays.asList(), LocalDateTime.now()),
                new Person("Tik", 12, null, LocalDateTime.now())
        );
    }

    @Test
    public void testCopySimpleClass() {
        PersonVO convert = BeanMapper.convert(getPersons().get(0), PersonVO.class);
        assertNotNull(convert);
    }

    @Test
    public void testCopyArray() {
        List<PersonVO> personVOS = BeanMapper.convertList(getPersons(), PersonVO.class);
        assertNotNull(personVOS);
    }
}
