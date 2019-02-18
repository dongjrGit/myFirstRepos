package com.yinlian.wssc.web.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apdplat.word.lucene.ChineseWordAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.dto.ProLuceneDto;
import com.yinlian.wssc.web.mapper.CategoryMapper;
import com.yinlian.wssc.web.mapper.SearchProductMapper;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.service.LuceneSearchService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Component("luceneSearchService")
public class LuceneSearchServiceImpl implements LuceneSearchService {

	@Autowired
	private SearchProductMapper searchProductMapper;
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public void createIndex() {
		try {
			List<ProLuceneDto> prolist = searchProductMapper.getprolist();
			if (prolist != null) {
				for (ProLuceneDto dto : prolist) {
					// 获取规格信息
					List<Specsvalues> valuesList = searchProductMapper
							.getvaluesByspuID(dto.getId());
					// 获取商品三级分类的信息
					List<Integer> isList = new ArrayList<Integer>();
					for (String id : dto.getFullpath().split(",")) {
						isList.add(StringUtilsEX.ToInt(id));
					}
					List<Category> claList = categoryMapper
							.getListbyids(isList);

					String keys = dto.getName() + ",";
					keys += dto.getShorttitle() + ",";
					keys += dto.getSpudesc() + ",";
					keys += dto.getShopname() + ",";
					keys += dto.getBrandname() + ",";
					// keys+=dto.getClassname()+",";
					keys += dto.getTag() + ",";

					if (claList != null) {
						for (Category category : claList) {
							keys += category.getName() + ",";
						}
					}
					if (valuesList != null) {
						for (Specsvalues sv : valuesList) {
							keys += sv.getValue() + ",";
						}
					}
					dto.setStrkeys(keys);
				}
				//word
				setData(prolist);
				//一元分词
				setData2(prolist);
			}
		} catch (Exception e) {
			LogHandle.error(LogType.searchPro, e);
		}
	}

	private void setData2(List<ProLuceneDto> list) throws Exception {

		// 判断文件夹是否存在
		File indexDir = new File(getdics());
		if (!indexDir.exists() && !indexDir.isDirectory()) {
			indexDir.mkdir();
		}
		// java 一元分词
		 Analyzer luceneAnalyzer =new StandardAnalyzer(Version.LUCENE_CURRENT);
		 IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_CURRENT,
		 luceneAnalyzer);

		 Directory directory = FSDirectory.open(indexDir);
		 if(IndexWriter.isLocked(directory)){
		 IndexWriter.unlock(directory);
		 }
		 IndexWriter writer = new IndexWriter(directory,iwc);
//		 先删除 再新增
		 writer.deleteAll();

		if (list != null) {
			for (ProLuceneDto dto : list) {
				Document document = new Document();
				// 不切词 索引 存储
				document.add(new StringField("id", dto.getId().toString(), Field.Store.YES));
				document.add(new StringField("classid", dto.getClassid()
						.toString(), Field.Store.YES));
				document.add(new StringField("brandid", dto.getBrandid()
						.toString(), Field.Store.YES));
				document.add(new StringField("shopid", dto.getShopid()
						.toString(), Field.Store.YES));
				// 切词 索引 存储
				document.add(new TextField("strkeys", dto.getStrkeys(),
						Field.Store.YES));
				document.add(new TextField("classname", dto.getClassname(),
						Field.Store.YES));
				document.add(new TextField("brandname", dto.getBrandname(),
						Field.Store.YES));
				document.add(new TextField("shopname", dto.getShopname(),
						Field.Store.YES));
				writer.addDocument(document);
			}
		}
		writer.commit();
		writer.close();
	}

	private void setData(List<ProLuceneDto> list) throws Exception {

		// 判断文件夹是否存在
		File indexDir = new File(getdic());
		if (!indexDir.exists() && !indexDir.isDirectory()) {
			indexDir.mkdir();
		}
		
		// word分词
		Analyzer analyzer = new ChineseWordAnalyzer();
//		Directory directory = new RAMDirectory();
//		if (list != null) {
//			for (ProLuceneDto dto : list) {
//				TokenStream tokenStream = analyzer.tokenStream("strkeys", dto.getStrkeys());
//				while(tokenStream.incrementToken()){
//				    CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
//				    OffsetAttribute offsetAttribute = tokenStream.getAttribute(OffsetAttribute.class);
//				    System.out.println(charTermAttribute.toString()+" "+offsetAttribute.startOffset());
//				}
//			}
//		}
		 Directory directory = FSDirectory.open(indexDir);
		 if(IndexWriter.isLocked(directory)){
		 IndexWriter.unlock(directory);
		 }
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, config);
		// 先删除 再新增
		indexWriter.deleteAll();

		if (list != null) {
			for (ProLuceneDto dto : list) {
				Document document = new Document();
				// 不切词 索引 存储
				document.add(new StringField("id", dto.getId().toString(), Field.Store.YES));
				document.add(new StringField("classid", dto.getClassid()
						.toString(), Field.Store.YES));
				document.add(new StringField("brandid", dto.getBrandid()
						.toString(), Field.Store.YES));
				document.add(new StringField("shopid", dto.getShopid()
						.toString(), Field.Store.YES));
				// 切词 索引 存储
				document.add(new TextField("strkeys", dto.getStrkeys(),
						Field.Store.YES));
				document.add(new TextField("classname", dto.getClassname(),
						Field.Store.YES));
				document.add(new TextField("brandname", dto.getBrandname(),
						Field.Store.YES));
				document.add(new TextField("shopname", dto.getShopname(),
						Field.Store.YES));
				
//				List<Word> words = WordSegmenter.segWithStopWords(dto.getStrkeys());
//				for (Word wd : words) {
//					LogHandle.error(LogType.searchPro, wd.toString(),"/segWithStopWords");
//				}
				
				indexWriter.addDocument(document);
			}
		}
		indexWriter.commit();
		indexWriter.close();
	}
	
	@Override
	public List<ProLuceneDto> getProidList(String keys) throws Exception {
	
		if(keys.length()>1){
			// 查找
			Directory directory = FSDirectory.open(new File(getdic()));
			IndexReader ir = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ir);
			QueryParser parser = new QueryParser(Version.LUCENE_47,"strkeys",new ChineseWordAnalyzer());
			Query query = parser.parse(keys);
			TopDocs docs = searcher.search(query, 1000);
			List<ProLuceneDto> prolist = new ArrayList<ProLuceneDto>();
			for (ScoreDoc sdoc : docs.scoreDocs) {
				Document doc = ir.document(sdoc.doc);
				ProLuceneDto spu = new ProLuceneDto();
				spu.setId(Integer.parseInt(doc.get("id")));
				spu.setClassid(Integer.parseInt(doc.get("classid")));
				spu.setClassname(doc.get("classname"));
				spu.setBrandid(Integer.parseInt(doc.get("brandid")));
				spu.setBrandname(doc.get("brandname"));
				spu.setShopid(Integer.parseInt(doc.get("shopid")));
				spu.setShopname(doc.get("shopname"));
				prolist.add(spu);
			}
			return prolist;
		}else{
			// 查找
			Directory directory = FSDirectory.open(new File(getdics()));
			IndexReader ir = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ir);
			QueryParser parser = new QueryParser(Version.LUCENE_47,"strkeys",new StandardAnalyzer(Version.LUCENE_47));
			Query query = parser.parse(keys);
			TopDocs docs = searcher.search(query, 1000);
			List<ProLuceneDto> prolist = new ArrayList<ProLuceneDto>();
			for (ScoreDoc sdoc : docs.scoreDocs) {
				Document doc = ir.document(sdoc.doc);
				ProLuceneDto spu = new ProLuceneDto();
				spu.setId(Integer.parseInt(doc.get("id")));
				spu.setClassid(Integer.parseInt(doc.get("classid")));
				spu.setClassname(doc.get("classname"));
				spu.setBrandid(Integer.parseInt(doc.get("brandid")));
				spu.setBrandname(doc.get("brandname"));
				spu.setShopid(Integer.parseInt(doc.get("shopid")));
				spu.setShopname(doc.get("shopname"));
				prolist.add(spu);
			}
			return prolist;
		}
	}

	
	private String getdics() throws Exception {
		Properties pro = new Properties();
		// 读取配置文件
		InputStream inStream = this.getClass().getClassLoader()
				.getResourceAsStream("cfg.properties");
		pro.load(inStream);
		return pro.getProperty("searchpaths");

	}
	private String getdic() throws Exception {
		Properties pro = new Properties();
		// 读取配置文件
		InputStream inStream = this.getClass().getClassLoader()
				.getResourceAsStream("cfg.properties");
		pro.load(inStream);
		return pro.getProperty("searchpath");

	}

}
