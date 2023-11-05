//package ru.netology

import Post
import WallService.posts
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

data class Post(
    var id: Int = 0,
    val ownerId: Int,
    val text: String,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit:Boolean = true,
    val isPinned: Boolean = false,
    val date: Int,
    val friendsOnly: Boolean = false,
    val postType: String = "post") {
    class Likes(
        var count: Int = 0,
        var canLike: Boolean = true,
        var canPublish: Boolean = true,
        var userLikes: Boolean = false
    )
}

class WallServiceTest {

//    @Before
//    fun clearBeforeTest() {
//        clear()
//    }

    @Test
    fun add() {
        val post = Post(id = 0,
        ownerId = 123,
        text = "hello",
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        date = 78965,
        friendsOnly = false,
        postType = "post")
        val lastId = 0

        val result = add(post)

        assertEquals(Post(id=1, ownerId=123, text="hello", canPin=true, canDelete=true, canEdit=true, isPinned=false, date=78965, friendsOnly=false, postType="post"), result)

    }

    @Test
    fun updateTrue() {
        val new = Post(ownerId = 123, id = 1, text = "hello", date = 123456)
        posts+=new

        val result = update(new, 1)

        assertEquals(true, result)
    }


    @Test
    fun updateFalse() {
        val new = Post(ownerId = 123, id = 1, text = "hello", date = 123456)
        posts+=new

        val result = update(new, 2)

        assertEquals(false, result)
    }
}