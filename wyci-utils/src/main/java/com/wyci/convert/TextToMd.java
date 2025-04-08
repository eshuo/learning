package com.wyci.convert;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-04-08 18:00
 * @Version V1.0
 */
public class TextToMd {

    private static final String PDF_STORAGE_PATH = System.getProperty("user.dir") + "/reports/pdf/";


    private static File fontFile;

    public static void main(String[] args) throws Exception {
//        String markdown = new String(Files.readAllBytes(Paths.get("sample.md")), StandardCharsets.UTF_8);

        String markdown = "# 生猪周报 2025-04-01至2025-04-08\n"
            + "\n"
            + "## 当日要闻综述\n"
            + "- 中办、国办印发《关于完善价格治理机制的意见》，提出健全生猪保险等政策协同机制 [来源1](https://www.boyar.cn/article/1225218.html)\n"
            + "- 四川省首家小型生猪屠宰企业通过《生猪屠宰质量管理规范》验收 [来源2](https://www.boyar.cn/article/1225104.html)\n"
            + "- 重庆大足区\"保险+期货\"项目为3.92万头生猪提供风险保障，赔付率达400.7% [来源3](https://futures.eastmoney.com/a/202504013361468217.html)\n"
            + "- 3月生猪市场价格走势强于预期，LH2509合约或成主力合约 [来源4](https://www.boyar.cn/article/1225097.html)\n"
            + "- 主要上市猪企3月销售数据公布：新希望销量155万头领跑，神农集团均价14.83元/公斤最高 [综合报道]\n"
            + "\n"
            + "## 详细分析\n"
            + "### 1. 生猪产业政策支持力度加大\n"
            + "中办、国办联合发文明确要求稳定政策性生猪保险供给，完善农资保供稳价机制。该政策与生产者补贴形成组合拳，旨在建立长效的价格风险分担机制。据文件显示，政策特别强调\"因地制宜\"原则，允许地方探索特色保险品种 [来源1]。结合重庆大足区\"保险+期货\"项目187.61万元的实际赔付效果，显示金融工具对稳定养殖收益的作用显著 [来源3]。\n"
            + "\n"
            + "### 2. 屠宰行业标准化进程加速\n"
            + "四川省西充宏鑫食品有限公司成为全国首个通过《规范》验收的小型屠宰企业，其改造重点包括：1）引入智能化设备；2）增加152项检验指标；3）升级污水处理系统。南充市计划年内完成59家屠宰场改造，占现有企业的80%。值得注意的是，小型企业占该市屠宰企业总量的75%，改造将显著提升区域肉品安全水平 [来源2]。\n"
            + "\n"
            + "### 3. 生猪市场供需格局变化\n"
            + "3月生猪市场呈现三大特征：1）集团企业出栏均重上调；2）二次育肥入场托底；3）冻品库存处于低位。据大北农公告，商品猪均价14.35元/公斤（环比+0.61%），神农集团达到14.83元/公斤 [来源5,7]。分析师认为，当前价格已部分消化4月抛压预期，LH2509合约可能在现货企稳后迎来反弹 [来源4]。\n"
            + "\n"
            + "## 数据汇总表\n"
            + "| 指标名称               | 数值          | 波动率(环比) | 来源编号 |\n"
            + "|-----------------------|---------------|-------------|---------|\n"
            + "| 大北农商品猪均价       | 14.35元/公斤  | +0.61%      | 5       |\n"
            + "| 神农集团商品猪均价     | 14.83元/公斤  | +0.14%      | 7       |\n"
            + "| 新希望生猪销量        | 155万头       | +36.03%     | 11      |\n"
            + "| 重庆保险项目覆盖率     | 3.92万头      | -           | 3       |\n"
            + "| 小型屠宰企业占比(南充) | 75%           | -           | 2       |\n"
            + "\n"
            + "## 新闻来源列表\n"
            + "1. [完善价格治理机制意见出台] -[来源链接](https://www.boyar.cn/article/1225218.html)(2025-04-01)\n"
            + "2. [四川小型屠宰企业通过验收] -[来源链接](https://www.boyar.cn/article/1225104.html)(2025-04-01)\n"
            + "3. [重庆生猪保险+期货项目] -[来源链接](https://futures.eastmoney.com/a/202504013361468217.html)(2025-04-01)\n"
            + "4. [3月生猪市场复盘分析] -[来源链接](https://www.boyar.cn/article/1225097.html)(2025-04-01)\n"
            + "5. [大北农3月销售数据] -[来源链接](https://finance.eastmoney.com/a/202504073367781127.html)(2025-04-07)\n"
            + "6. [大北农销售数据更新] -[来源链接](https://finance.eastmoney.com/a/202504073367711382.html)(2025-04-07)\n"
            + "7. [神农集团销售数据] -[来源链接](https://finance.eastmoney.com/a/202504073367551396.html)(2025-04-07)\n"
            + "8. [巨星农牧销售数据] -[来源链接](https://finance.eastmoney.com/a/202504073367528327.html)(2025-04-07)\n"
            + "9. [神农集团投资项目] -[来源链接](https://finance.eastmoney.com/a/202504013362461482.html)(2025-04-01)\n"
            + "10. [金新农销售数据] -[来源链接](https://finance.eastmoney.com/a/202504073367787287.html)(2025-04-07)\n"
            + "11. [新希望销售数据] -[来源链接](https://finance.eastmoney.com/a/202504073367757574.html)(2025-04-07)\n"
            + "12. [大北农数据补充] -[来源链接](https://finance.eastmoney.com/a/202504073367725979.html)(2025-04-07)\n"
            + "13. [金新农数据更新] -[来源链接](https://finance.eastmoney.com/a/202504073367708344.html)(2025-04-07)\n"
            + "14. [大北农最终公告] -[来源链接](https://finance.eastmoney.com/a/202504073367706360.html)(2025-04-07)";

        String html = convertMarkdownToHtml(markdown);
        String string = PDF_STORAGE_PATH + System.currentTimeMillis();
        saveHtmlForDebug(html, string + ".html");
        createPdf(html, string + ".pdf");
        System.out.println("✅ PDF 生成成功: output.pdf");
    }

    public static String convertMarkdownToHtml(String markdown) {
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        String body = renderer.render(parser.parse(markdown));

        // 添加 HTML 样式包装
        return "<html><head>" +
            "<meta charset=\"UTF-8\" />" +
            "<style>" +
            "body { font-family: 'Noto Sans SC', sans-serif; font-size: 14px; padding: 20px; }" +
//            "body { font-family: 'simsun', sans-serif; font-size: 14px; padding: 20px; }" +
            "table { border-collapse: collapse; width: 100%; table-layout: fixed; word-break: break-word; }" +
            "th, td { border: 1px solid #666; padding: 8px; text-align: left; vertical-align: top; }" +
            "th { background-color: #f2f2f2; }" +
            "</style></head><body>" +
            body + "</body></html>";
    }

    public static void createPdf(String html, String outputPdfPath) throws IOException {
        try (OutputStream os = new FileOutputStream(outputPdfPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            if (null == fontFile) {
                ClassPathResource classPathResource = new ClassPathResource("fonts/NotoSansSC-VariableFont_wght.ttf");
//                ClassPathResource classPathResource = new ClassPathResource("fonts/simsun.ttc");
                InputStream inputStream = classPathResource.getInputStream();
                fontFile = asFile(inputStream);//手动转换：InputStream To File
            }
            builder.useFont(fontFile, "Noto Sans SC");
//            builder.useFont(fontFile, "simsun");
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File asFile(InputStream in) throws IOException {
//        File tempFile = File.createTempFile("simsun", ".ttc");
        File tempFile = File.createTempFile("NotoSansSC", ".ttf");
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.copy(in, out);
        return tempFile;
    }

    public static void saveHtmlForDebug(String html, String filePath) {
        try {
            Files.write(Paths.get(filePath), html.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
