import WallService.add
import WallService.lastId
import WallService.posts
import WallService.update

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
    val postType: String = "post",
    val likes: Likes = Likes()) {
    class Likes(
        var count: Int = 0,
        var canLike: Boolean = true,
        var canPublish: Boolean = true,
        var userLikes: Boolean = false
    )
}
object WallService {
    public var posts = emptyArray<Post>()
    public var lastId: Int = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId)
        return posts.last()
    }

    fun update(post: Post, id: Int): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        lastId = 0
        posts = emptyArray()
    }

}

fun main() {

    val new = Post(ownerId = 123, id = 1, text = "hello", date = 123456)
    val old = Post(ownerId = 13, id = 2, text = "hllo", date = 12456)
    println(add(new))
    println(add(old))
    println(update(new, 1))
    println(update(old, 45))
    println(new.id)
    println(old.id)
}

