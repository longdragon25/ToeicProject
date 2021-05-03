package vn.myclass.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name ="listenguideline" )
public class ListenGuideLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listenGuideLineId;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "createddate")
    private Timestamp createddate;

    @Column(name = "modifiedDate")
    private Timestamp modifiedDate;

    @OneToMany(mappedBy = "listenGuideLineEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    public List<CommentEntity> getCommentList() {
        return commentEntityList;
    }

    public void setCommentList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    //    @ManyToOne
//    @JoinColumn(name = "roleid")
//    private RoleEntity role;

    public Integer getListenGuideLineId() {
        return listenGuideLineId;
    }

    public void setListenGuideLineId(Integer listenGuideLineId) {
        this.listenGuideLineId = listenGuideLineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
