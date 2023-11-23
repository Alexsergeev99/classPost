import WallService.add
import WallService.createComment
import WallService.id
import WallService.posts
import WallService.update
import java.lang.RuntimeException

data class Post(
    var id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val text: String?,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit:Boolean = true,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val date: Int?,
    val friendsOnly: Boolean = false,
    val postType: String = "post",
    val signerId: Int,
    val postponedId: Int = 0,
    val likes: Likes = Likes(),
    val comments: Comments = Comments(),
    val copyright: Copyright = Copyright(),
    val reposts: Reposts = Reposts(),
    val copyHistory: Array<Reposts>? = emptyArray(),
    val views: Views = Views(),
    val postSource: PostSource = PostSource(),
    val geo: Geo = Geo(),
    val donut: Donut = Donut(),
    val attachments: Array<Attachment> = arrayOf( VideoAttachment(),
                                                  PhotoAttachment(),
                                                  AudioAttachment(),
                                                  FileAttachment(),
                                                  PresentAttachment())
)
  {
    class Likes(
        var count: Int = 0,
        var canLike: Boolean = true,
        var canPublish: Boolean = true,
        var userLikes: Boolean = false
    )
    open class Comments(
        var counter: Int = 0,
        var canPost: Boolean = true,
        var groupsCanPost: Boolean = false,
        var canClose:Boolean = true,
        var canOpen:Boolean = true,
    )
    class Copyright(
        var copyId: Int = 0,
        var link: String? = null,
        var name: String? = null,
        var type: String? = null
    )
    class Reposts(
        var repostCounter: Int = 0,
        var userReposted: Boolean = false
    )

    class Views(
        val viewCounter: Int = 0
    )

      class PostSource (
          var Standalone: Boolean = false
      )

      class Geo(
          var type: String? = null,
          var coordinates: String? = null,
          var place: Place = Place()
      ) {
          class Place(
              var placeId: Int = 0,
              val title: String? = null,
              var latitude: Int = 0,
              var longitude: Int = 0,
              var created: Int = 0,
              var icon: String? = null,
              var checkins: Int = 0,
              var updated: Int = 0,
              var type: Int = 0,
              var country: Int = 0,
              var city: Int = 0,
              var address: String? = null
          )
      }

      class Donut(
          var isDonut: Boolean = false,
          var paidDuration: Int = 10,
          var canPublishFreeCopy: Boolean = false,
          var editMode: String = "all"
      )

  }
interface Attachment {
    var attachmentType: String
}
data class VideoAttachment(
    override var attachmentType: String = "video",
    val video: Video = Video(),
):Attachment

data class Video(
    val videoId: Int = 1,
    val videoOwnerId: Int = 1,
    val videoTitle: String? = null,
    val duration: Int = 10,
    val description: String? = null
)
data class PhotoAttachment(
    override var attachmentType: String = "photo",
    val photo: Photo = Photo()): Attachment

data class Photo(
    val photoId: Int = 1,
    val photoUserId: Int = 1,
    val photoText: String? = null,
    val photoOwnerId: Int = 1,
    val dateOfPhoto: Int? = null
)
data class AudioAttachment(
    override var attachmentType: String = "audio",
    val audio: Audio = Audio()): Attachment

data class Audio(
    val audioId: Int = 1,
    val audioOwnerId: Int = 1,
    val artist: String? = null,
    val audioTitle: String? = null,
    val audioDuration: Int = 60)

data class FileAttachment(
    override var attachmentType: String = "file",
    val file: File = File()): Attachment

data class File(
    val fileId: Int = 1,
    val fileOwnerId: Int = 1,
    val fileTitle: String? = null,
    val exp: String? = null,
    val fileSize: Int = 150)

data class PresentAttachment(
    override var attachmentType: String = "present",
    val present: Present = Present()): Attachment

data class Present(
    val presentId: Int = 1,
    val thumb_256: String? = null,
    val thumb_96: String? = null,
    val thumb_48: String? = null,
    )
class PostNotFoundException(message: String): RuntimeException(message)
object WallService {
    public var posts = emptyArray<Post>()
    public var comments = emptyArray<Comment>()
    public var lastId: Int = 0
    public var id: Int = 0
    data class Comment(
        val text: String
    )


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
    fun createComment(postId: Int, comment: Comment): Comment {
        for (element in posts) {
            if (postId == element.id) {
                comments += comment
                return comments.last()
            }
        }
        throw PostNotFoundException(("Пост ID:$postId не найден"))
    }

    fun clear() {
        lastId = 0
        posts = emptyArray()
        comments = emptyArray()
    }

}

fun main() {

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
    val old = Post(
        ownerId = 13,
        id = 2,
        text = "hllo",
        date = 12456,
        fromId = 45,
        createdBy = 690,
        replyOwnerId = 980,
        replyPostId = 997,
        signerId = 500)
    println(add(new))
    println(add(old))
    println(update(new, 1))
    println(update(old, 45))
    println(new.id)
    println(old.id)
    println(posts.last())
    println(createComment(45, WallService.Comment("hello")))
}

