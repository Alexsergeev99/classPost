//package ru.netology

import Post
import WallService.posts
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val new = Post(ownerId = 123, id = 1, text = "hello", date = 123456)

        val result = WallService.add(new)

        assertEquals(1, result.id)

    }

    @Test
    fun updateTrue() {
        val new = Post(ownerId = 123, id = 1, text = "hello", date = 123456)
//        posts+=new
        WallService.add(new)
        val result = WallService.update(new, 1)

        assertTrue(result)
    }


    @Test
    fun updateFalse() {
        val new = Post(ownerId = 123, id = 1, text = "hello", date = 123456)
//        posts+=new
        WallService.add(new)
        val result = WallService.update(new, 2)

        assertFalse(result)
    }
}