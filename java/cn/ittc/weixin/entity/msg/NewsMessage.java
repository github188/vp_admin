/**
 * <p>Title: NewsMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.msg;

import java.util.List;

/**
 * <p>Title: NewsMessage</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class NewsMessage  extends BaseMessage{
	// ͼ����Ϣ����������Ϊ10������ 
    private int ArticleCount;  
    // ����ͼ����Ϣ��Ϣ��Ĭ�ϵ�1��Ϊ��ͼ
    private List<Article> Articles;  
  
    public int getArticleCount() {  
        return ArticleCount;  
    }  
  
    public void setArticleCount(int articleCount) {  
        ArticleCount = articleCount;  
    }  
  
    public List<Article> getArticles() {  
        return Articles;  
    }  
  
    public void setArticles(List<Article> articles) {  
        Articles = articles;  
    }
}
