package 基础.拓展;



import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 爬虫 {
    public static void main(String[] args) throws IOException {
        //----------------------------------------------------------------
        //创建url对象
        URL url = new URL("https://ilive.lenovo.com.cn/?f=stee");
        //连接网址
        URLConnection urlConnection = url.openConnection();
        //读取网络数据
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream())
        );
        String line;
        Pattern pattern = Pattern.compile("<body>.*</body>");////这个页面只有一行
        while ((line = bufferedReader.readLine()) != null) {//按行读取数据
            //正则表达式进行匹配筛选数据
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
            System.out.println(line);
        }
        bufferedReader.close();//关闭读取器

        //----------------------------------------------------------------
        /*
        爬姓氏+男名/女名
         */
        //1.网址
        String familyNameUrl = "";
        String boyNameUrl = "";
        String girlNameUrl = "";
        //2.获取网页数据
        String familyNameStr = webCrawler(familyNameUrl);
        String boyNameStr = webCrawler(boyNameUrl);
        String girlNameStr = webCrawler(girlNameUrl);
        //3.正则表达式初步筛选数据
        ArrayList<String> familyNameTempList = getData(familyNameStr, "(.{4})(，|。)", 1);//获取第一组,前面四位
        ArrayList<String> boyNameTempList = getData(boyNameStr, "([\u4E00-\u9FA5]{2})(、|。)", 1);//获取第一组,2位的名字
        ArrayList<String> girlNameTempList = getData(girlNameStr, "(.. {4})..", 0);//获取所有
        //4.最终数据
        ArrayList<String> familyNameList = new ArrayList<>();
        ArrayList<String> boyNameList = new ArrayList<>();
        ArrayList<String> girlNameList = new ArrayList<>();

        for (String str : familyNameTempList) {
            for (int i = 0; i < str.length(); i++) {
                familyNameList.add(str.charAt(i) + "");
            }
        }
        for (String str : boyNameTempList) {
            if (!boyNameList.contains(str)) {
                boyNameList.add(str);
            }
        }
        for (String str : girlNameTempList) {
            String[] arr = str.split(" ");
            girlNameList.addAll(Arrays.asList(arr));
        }
        //5.生成指定个数不重复随机名字
        int boyCount = 50;
        int girlCount = 30;
        ArrayList<String> nameList = getInfo(familyNameList, boyNameList, girlNameList, boyCount, girlCount);
        Collections.shuffle(nameList);
        //6.写入到文件
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("name.txt"));
        for (String str:nameList){
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        //--------------------
        //Hutool包爬取页面(HttpUtil,ReUtil导不进来)
        //String listContent = HttpUtil.get(familyNameUrl);
        //List<String>list= ReUtil.findAll("",listContent,1);

    }

    private static ArrayList<String> getInfo(ArrayList<String> familyNameList, ArrayList<String> boyNameList, ArrayList<String> girlNameList, int boyCount, int girlCount) {
        HashSet<String> boyhs = new HashSet<>();//不重复随机名字
        while (true) {
            if (boyhs.size() == boyCount) {//生成个数
                break;
            }
            //随机一个名字
            Collections.shuffle(familyNameList);
            Collections.shuffle(boyNameList);
            boyhs.add(familyNameList.get(0) + boyNameList.get(0));
        }
        HashSet<String> girlhs = new HashSet<>();//不重复随机名字
        while (true) {
            if (girlhs.size() == girlCount) {//生成个数
                break;
            }
            //随机一个名字
            Collections.shuffle(familyNameList);
            Collections.shuffle(girlNameList);
            girlhs.add(familyNameList.get(0) + girlNameList.get(0));
        }
        ArrayList<String> list = new ArrayList<>();
        Random random = new Random();
        for (String boyName : boyhs) {
            int age = random.nextInt(10) + 18;
            list.add(boyName + "-男-" + age);
        }
        for (String girlName : girlhs) {
            int age = random.nextInt(8) + 18;
            list.add(girlName + "-女-" + age);
        }
        return list;
    }

    private static ArrayList<String> getData(String str, String regex, int index) {
        ArrayList<String> list = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(str);//文本匹配器
        while (matcher.find()) {
            list.add(matcher.group(index));
        }


        return list;
    }

    public static String webCrawler(String net) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //连接网络
        URL url = new URL(net);
        URLConnection urlConnection = url.openConnection();
        //读
        InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
        int ch;
        while ((ch = inputStreamReader.read()) != -1) {
            stringBuilder.append((char) ch);
        }
        inputStreamReader.close();

        return stringBuilder.toString();
    }
}
