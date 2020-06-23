package com.dimple.utils;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import static com.dimple.utils.FileUtil.getExtensionName;
import static com.dimple.utils.FileUtil.getFileNameNoEx;
import static com.dimple.utils.FileUtil.getSize;
import static com.dimple.utils.FileUtil.toFile;
import static org.junit.Assert.assertEquals;

/**
 * @className: FileUtilTest
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public class FileUtilTest {

    @Test
    public void testToFile() {
        long retval = toFile(new MockMultipartFile("foo", (byte[]) null)).getTotalSpace();
        assertEquals(500695072768L, retval);
    }

    @Test
    public void testGetExtensionName() {
        assertEquals("foo", getExtensionName("foo"));
        assertEquals("exe", getExtensionName("bar.exe"));
    }

    @Test
    public void testGetFileNameNoEx() {
        assertEquals("foo", getFileNameNoEx("foo"));
        assertEquals("bar", getFileNameNoEx("bar.txt"));
    }

    @Test
    public void testGetSize() {
        assertEquals("1000B   ", getSize(1000));
        assertEquals("1.00KB   ", getSize(1024));
        assertEquals("1.00MB   ", getSize(1048576));
        assertEquals("1.00GB   ", getSize(1073741824));
    }
}
