package com.micropos.products.repository;

import com.micropos.products.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public class JDRepository implements ProductRepository {


    private List<Product> products = null;

    @Override
    public List<Product> getProducts() {
        try {
            if (products == null)
                products = parseJD("Java");
        } catch (IOException e) {
            products = new ArrayList<>();
        }
        return products;
    }

    @Override
    public Product getProduct(String productId) {
        for (Product p : getProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> parseJD(String keyword) throws IOException {
        //获取请求https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keyword;

        // 发起 HTTP 请求并添加 Cookie
        Connection connection = Jsoup.connect(url);
        connection.cookie("thor",
                "4FEF95A4D737D9C79AB228140D98747AE0242AAB803E3CD46A679A2850F6DB7F9721889299BCB4B5807BF3744CAA91C5103DEE3699D7D60909F8E0F041E700D30C4917156BC374340010923510721E6315EE786162CE3BD86522C17069B08EBF68A60C86AEB1D158E70F2F70A0A25DD0F719C0D3645FDADC39E97349DBE736FD01B5F2CE02A3E63895D07F6EC6546C0EDBEACD9158CA67D37CF56C83D60D3E1B");
        connection.cookie("flash",
                "flash=2_yDhR1G_w-oQPFBr0apmQXF6dWFeXpR_yKMz5waIKel18Z3Z-nfeEI9TkIQrG5ZDIud6piKfPd29eqlRrz1Gr-KjVCf3fIi1wpO3QdauOJDlJHXp5n9jRQHDGrtyEWNZF");
        connection.cookie("jsavif",
                "1");
        connection.cookie("wlfstk_smdl",
                "2jgpljvfzprzhvar5zqx03n5gefal10o");
        connection.cookie("rkv",
                "1.0");
        connection.cookie("__jdc",
                "143920055");
        connection.cookie("pinId",
                "hbp2jLqcT_eNG0Jg2RFgvrV9-x-f3wj7");

        // 获取响应内容
        Document document = connection.get();

        //解析网页
        // Document document = Jsoup.parse(new URL(url), 10000);
        //所有js的方法都能用
        Element element = document.getElementById("J_goodsList");
        //获取所有li标签
        Elements elements = element.getElementsByTag("li");
        List<Product> list = new ArrayList<>();

        //获取元素的内容
        for (Element el : elements
        ) {
            //关于图片特别多的网站，所有图片都是延迟加载的
            String id = el.attr("data-spu");
            String img = "https:".concat(el.getElementsByTag("img").eq(0).attr("data-lazy-img"));
            String price = el.getElementsByAttribute("data-price").text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            if (title.indexOf("，") >= 0)
                title = title.substring(0, title.indexOf("，"));

            Product product = new Product(Long.parseLong(id), title, Double.parseDouble(price), img);

            list.add(product);
        }
        return list;
    }

}

