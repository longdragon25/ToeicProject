package vn.myclass.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "listenguidelineid")
    private ListenGuideLineEntity listenGuideLineEntity;



    @Column(name = "createddate")
    private Timestamp createdDate;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ListenGuideLineEntity getListenGuideLine() {
        return listenGuideLineEntity;
    }

    public void setListenGuideLine(ListenGuideLineEntity listenGuideLineEntity) {
        this.listenGuideLineEntity = listenGuideLineEntity;
    }


}
