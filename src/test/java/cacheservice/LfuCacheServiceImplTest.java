package cacheservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import com.javaprogram.moduleeffectivejava.cacheservice.CacheObject;
import com.javaprogram.moduleeffectivejava.cacheservice.service.CacheService;
import com.javaprogram.moduleeffectivejava.cacheservice.service.impl.LfuCacheServiceImpl;

public class LfuCacheServiceImplTest {

    public static final int CAPACITY = 2;
    private final CacheService sut = new LfuCacheServiceImpl(CAPACITY);

    @Test
    void shouldCacheProperlyViaLfuStrategy() {
        sut.put(1, new CacheObject("TestValue1"));
        sut.put(2, new CacheObject("TestValue2"));

        assertThat(sut.get(1).getValue(), is("TestValue1"));

        sut.get(1);
        sut.get(1);

        sut.put(3, new CacheObject("TestValue3"));

        assertThat(sut.get(2), nullValue());
        assertThat(sut.get(1).getValue(), is("TestValue1"));
    }

}
