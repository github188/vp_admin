/**
 * <p>Title: NewsMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.msg;

import java.util.List;

/**
 * <p>Title: NewsMessage</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月31日
 */
public class NewsMessage  extends BaseMessage{
	// 图文消息个数，限制为10条以内 
    private int ArticleCount;  
    // 多条图文消息信息，默认第1条为大图
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
