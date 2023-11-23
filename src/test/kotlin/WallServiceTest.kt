//package ru.netology

import Post
import WallService.comments
import WallService.createComment
import WallService.lastId
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
        val new = Post(
            ownerId = 123,
            id = 1,
            text = "hello",
            date = 123456,
            fromId = 234,
            createdBy = 678,
            replyOwnerId = 890,
            replyPostId = 657,
            signerId = 499)

        val result = WallService.add(new)

        assertEquals(1, result.id)

    }

    @Test
    fun updateTrue() {
        val new = Post(
            ownerId = 123,
            id = 1,
            text = "hello",
            date = 123456,
            fromId = 234,
            createdBy = 678,
            replyOwnerId = 890,
            replyPostId = 657,
            signerId = 499)
//        posts+=new
        WallService.add(new)
        val result = WallService.update(new, 1)

        assertTrue(result)
    }


    @Test
    fun updateFalse() {
        val new = Post(
            ownerId = 123,
            id = 1,
            text = "hello",
            date = 123456,
            fromId = 234,
            createdBy = 678,
            replyOwnerId = 890,
            replyPostId = 657,
            signerId = 499)
//        posts+=new
        WallService.add(new)
        val result = WallService.update(new, 2)

        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow(){
        val new = Post(
            ownerId = 123,
            id = 1,
            text = "hello",
            date = 123456,
            fromId = 234,
            createdBy = 678,
            replyOwnerId = 890,
            replyPostId = 657,
            signerId = 499)
        WallService.add(new)
//        val result = createComment(2, WallService.Comment("hello"))
        println(createComment(2, WallService.Comment("hello")))
    }

    @Test
    fun shouldNotThrow(){
        val new = Post(
            ownerId = 123,
            id = 1,
            text = "hello",
            date = 123456,
            fromId = 234,
            createdBy = 678,
            replyOwnerId = 890,
            replyPostId = 657,
            signerId = 499)
        WallService.add(new)
        val result = createComment(1, WallService.Comment("hello"))
        assertEquals(WallService.Comment(text = "hello"), result)
    }
}