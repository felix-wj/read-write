package cn.wangjie.learn;

import cn.wangjie.learn.entity.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.util.regex.Pattern.compile;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-01-10 21:45
 **/
@Slf4j
public class SampleTest {

    @Test
    public void testEnum() {
        String str = "abc";
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.reverse();
        System.out.println(stringBuffer);
        Suit.values();
    }

    enum Suit {
        a,
        b,
        c,
    }
    @Test
    public void shift() {
        int i = 0;
        while (true) {
            if ((i >>> 16) > 0) {
                System.out.println(i);
                break;
            }
            i++;
        }
        System.out.println(65535 >>> 16);

    }

    String str = "<h1>如果说</h1><p><br/></p><h1>你是最美的烟火海鸥岛据我看到那会滴啊面小孩点击安慕希不能改哈的就</h1><p><br/></p><p>\n" +
            "    逝去历史的在岁月中从容逝去，我们在书海中遨游终究不如亲自用脚步去丈量这段时光的跨度。</p><p>\n" +
            "    翻开历史的册页，在那些浩如烟海的典籍中找寻，我们发现，古道，作为古人运往行来之途，脚下是历史的呼唤，耳边是宇宙的呢喃，过往与未来在此刻交汇。</p><p>{document}70{/document}</p><p>\n" +
            "    刚和我爱的厚爱现货金爱都会<br/></p><p>{document}165{/document}</p><p>喜欢吗<br/></p><p>{product}24731{/product}</p><p><br/></p><p>\n" +
            "    目的地<br/></p><p>{mdd}16{/mdd}</p><p><br/></p><p>集市</p><p>{shop}47{/shop}</p><p>线路</p><p></p><h2>这里是二级目录</h2><h1>\n" +
            "    <strong>前言</strong></h1><p>你是否想带孩子去国外度过一个与众不同的假期？这个暑期，辣妈萌爸们不妨带上孩子们漂洋过海，来一次难忘的童话之旅，疯玩去吧！</p><p>\n" +
            "    陪孩子看世界，除了目的地景点的选择，辣妈萌爸们最关心的还是住宿问题。如果有那么一个住处，不仅可以让孩子们舒适地休息，还可以随时随地玩high，是不是你梦寐以求的呢？</p><p>\n" +
            "    一起盘点国外亲子游正流行的3大住宿体验，去发现亲子游好去处！</p><p><img src=\"http://qimg4.youxiake.com/15591096090.png\"/></p><p><span\n" +
            "        style=\"font-size: 10px;\">图片来自：稀饭</span></p><h1><strong>主题乐园</strong></h1><p>\n" +
            "    一个适合全家入住的亲子主题酒店，会让亲子关系继续升温，激发孩子更多兴趣。</p><p>\n" +
            "    超萌的小黄人主题套房、哈利波特的魔法庄园、波音747改建的飞机旅馆，还有少女们最爱的梦幻芭比套房…这些不仅可以满足每个小天使的纯真幻想，也让父母们重拾童心。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096092.png\"/></p><p><img src=\"http://qimg4.youxiake.com/15590975867.png\"/>\n" +
            "</p><h2><strong>新山乐高乐园</strong><strong>&nbsp;|&nbsp;马来西亚</strong></h2><p>\n" +
            "    乐高公园在全球已经建有8家主题公园，地点覆盖欧洲、美国、日本、迪拜、东南亚，这里不仅是乐高积木的汇聚，更是用人类无边的创造力，建造出的快乐源泉。</p><p>\n" +
            "    马来西亚的新山乐高乐园，应该是国内朋友们打卡最多的一家乐高乐园了。这里距离新加坡仅有一个多小时的车程，是去新山的必玩景点之一。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/155910961010.png\"/></p><p>乐高乐园酒店坐落在乐高乐园的正门旁，青色的乐高飞龙，超巨型乐高武士，都在酒店大门口热烈迎接你的到来。</p>\n" +
            "<p>一踏入酒店大堂，宏伟的乐高海盗船映入眼帘，大堂边有个古代欧式乐高城堡，供小孩玩乐。其实酒店大堂本身就是一个迷你玩乐天地，在这里小孩忙着叠乐高积木，发挥无限的想象力，欢乐的笑声不绝于耳。<br/></p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096102.png\"/></p><p>酒店共有所有258间客房都是主题套房，客人人可以从四个著名的乐高系列主题 -\n" +
            "    海盗、城堡，探险或幻影忍者主题中做选择。</p><p>客房内设有两个独立的睡眠区，包括供大人睡的一张特大床，以及最多可容纳三个小孩的独立睡眠区，备有一张双层床、收放式床和电视。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096109.png\"/></p><p><img src=\"http://qimg4.youxiake.com/15591096118.png\"/>\n" +
            "</p><p><img src=\"http://qimg4.youxiake.com/15591096116.png\"/></p><p><strong>时差：</strong></p><p>与北京没有时差；飞行时间约6小时<br/></p>\n" +
            "<p><strong>就餐时间：</strong></p><p>孩子们一定喜欢在美食中心吃饭，生机勃勃的室内市场挤满了供应马来、印度、中国和印度尼西亚特色佳肴的食品摊档。</p><p>\n" +
            "    需要注意的是，食物有可能是辣的，但腌渍后烧烤的牛肉串和鸡肉串可以放心吃，一般来说孩子们也都会很喜欢。甜食方面，小美食家们可以品尝各种不同的热带水果，比如红毛丹、火龙果，或是冒点险试试榴莲。</p><p><strong>孩子们的纪念品：</strong>\n" +
            "</p><p>风筝：是马来西亚人所珍视的东西，国内有许许多多专门的相关节庆</p><p>用干水牛皮做的玩偶</p><p>传统陀螺</p><p></p><h2><strong>棕榈岛亚特兰蒂斯 |&nbsp;</strong><strong>迪拜</strong>\n" +
            "</h2><p>迪拜是爱玩水的孩子的天堂，为了满足孩子们的需求，这里的酒店都盖起了一座座水上乐园，住在这样的酒店，可能要留给孩子一整天玩耍的时间才够哦！</p><p>\n" +
            "    棕榈岛亚特兰蒂斯酒店单是玩水就让一家人有多种选择。去水世界冒险乐园（Aquaventure Waterpark）玩各式滑梯，小朋友们在Splasher’s 玩小滑梯，胆子大的孩子可以去挑战刺激的大滑梯。</p><p>\n" +
            "    人气项目Aquaconda形如水蛇，总长度达到210米；Leap of Faith则以高度吸睛，高27.5米的滑梯几乎垂直，一入滑道便有强烈的失重感。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096127.png\"/></p><p>在“失落的空间”水族馆（The Lost Chambers\n" +
            "    Aquarium），65000尾海洋生物为你展示了神秘的海洋世界。除了隔着玻璃观看，大人还可以带着孩子穿上潜水服，走进海洋亲自感受海底世界。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096122.png\"/></p><p><img src=\"http://qimg4.youxiake.com/155910961210.png\"/>\n" +
            "</p><p>体验这么多游乐项目后，来到酒店房间休息，窗外就是大海，视野极佳。如果还想感受海洋气息，不如预订水下套房，房间内就有一个大的水族箱，躺在床上看鱼儿游来游去，孩子们定会为这样的房子所吸引！</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096131.png\"/></p><p><strong>时差：</strong></p><p>比北京时间晚4小时；飞行时间约10小时</p><p>\n" +
            "    <strong>就餐时间：</strong></p><p>迪拜的饮食深受黎巴嫩影响。孩子们会十分享受小拼盘（mezze）和什锦烧烤（mixed grills），它们让人能够一次就品尝到好几种不同的菜肴。</p><p>\n" +
            "    Shwarma（用皮塔饼做的馅饼）到处都有，而且全都很好吃。馅饼饱满，价格还很便宜。来自伊朗的特色美食或许值得尝试，有的孩子十分喜爱这种香料与甜美可口滋味的混合。</p><p>\n" +
            "    这里有那么多主打各国美食（欧洲、印度、美国、俄罗斯、中国）的餐厅，纵是众口难调，也能让食客各得其所。无论如何，大部分餐厅都会提供儿童菜单。</p><p><strong>孩子们的纪念品：</strong></p><p>\n" +
            "    芭蕾舞鞋、头冠和缀着亮片的衣服，穿上就像山鲁佐德（《一千零一夜》里苏丹的新娘）一样</p><p>毛绒玩具骆驼，有大有小，还有超大号的</p><p></p><h2>阿苏馒头屋&nbsp;|&nbsp;日本九州</h2><p>\n" +
            "    熊本部长在火山下造了一个童话镇，馒头屋连绵成群，欢迎你来住！阿苏，像极了人名的一座小城。如果说东京、大阪是日本国的都市，阿苏是乡下的乡下。</p><p>\n" +
            "    有人说旅行是睡过去的，那你该来阿苏农场与星月共枕。作为日本超人气的吉祥物，熊本熊在全宇宙拥有众多迷弟迷妹，这让熊本县迅速蹿红成了日本最受欢迎的观光城市，阿苏市便是熊本县下面的一个小城市。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096132.png\"/></p><p>\n" +
            "    看阿苏是个小地方，这里每年都要接待近500万的游客，并且已经连续多年成为日本最受欢迎的城市第四名。<strong>之所以这么火，完全是因为熊本县的主人熊本熊，在这里建了一座“馒头村”——阿苏农场。</strong></p><p>\n" +
            "    阿苏农场位于阿苏国立公园内，在海拔550米的农场占地面积达到了2000坪，是全世界独有的大自然主题公园。农场主题定位为“人、自然、元气”三大元素，与自然揉为一体。从阿苏山上南麓看过去，可以看到整个农场。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096146.png\"/></p><p>\n" +
            "    每一个来到阿苏农场的都被被一栋栋造型奇特的圆形小房子吸引。总共有450多间馒头屋坐落在农庄，像极了七龙珠里面的那美克星球。</p><p>\n" +
            "    据说这些酷似馒头的小屋原本是熊本县一家果子店老板的奇想，利用日本人对于圆形代表圆满的意义作为外观造型，希望每一位住客都能幸福美满。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096145.png\"/></p><p><strong>时差：</strong></p><p>比北京早1小时；飞行时间约3小时</p><p>\n" +
            "    <strong>就餐时间：</strong></p><p>日本的食物美味可口而且花样繁多：除了shokudo（家庭餐馆）和zakaya（居酒屋）外，还有专门经营某一种食物的餐厅。</p><p>\n" +
            "    孩子们会发现他们能在yakitori-ya（烧鸟屋）、ramen-ya（拉面馆）以及sushi-ya（寿司店）里找到想吃的东西。此外还可以尝试Okonomiyaki（御好烧）和omuraisu（蛋包饭）。</p><p>\n" +
            "    <strong>孩子们的纪念品：</strong></p><p>小摆设、小玩具、装饰品——哪怕是最便宜的东西，细节上的用心也让人很难抗拒这些当地的小玩意儿。</p><p>\n" +
            "    Ningyo（传统玩偶）都是小型的艺术品，有瓷的、木头的和步做的；还可以选择忍者或武士玩偶。</p><p></p><h1><strong>星空露营</strong></h1><p>\n" +
            "    回归质朴简单的生活，露营是理想的选择。这样的旅行方式可以让一家人共享轻松惬意的时光，并且真正地了解一个国家。<br/></p><p>\n" +
            "    就连最小的家庭成员也能一同参加露营之旅，可以跟随他们的生活步调，晚上早些入睡，日出起床。抬头便是星空，睡醒即见红日，这是最自由的一种游玩，也是最 的一种生活态度。</p><p>\n" +
            "    选择露营地时应考虑孩子的年龄和一家人想要的是怎样的假期。海滩附近热闹的大型露营地或是乡下野外的小型露营地。与孩子一起体验一次特别的露营，他会比你想象的要兴奋得多！</p><h2>\n" +
            "    日本&nbsp;|&nbsp;glamping</h2><p>「豪华露营」(Glamping)，就是Glamorous和Camping组合在一起的新字。这是以欧美为发源地，近来逐渐流行成为最新类型的露营方式。</p><p>\n" +
            "    说到一般的露营，不外乎就是要花费心思在准备帐篷以及调理道具；但如果是豪华露营，就可使用专门设备，即便是两手空空什么也没带，也能够轻松出去游玩。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/155910961510.png\"/></p><p><br/></p><p>这里插入了一个专题</p><p>\n" +
            "    {document}178{/document}</p><p>早上被鸟鸣声唤醒，下午在大自然中过一段非凡的时光，晚上睡在一个被火炉围着的漂亮帐篷里。</p><p>\n" +
            "    舒服优雅地享受户外活动的「豪华露营」(Glamping)在日本很受欢迎，亦有层出不穷的崭新景点。</p><p>位于绿意盎然的环境之中或湖畔地带的豪华露营景点，以一种崭新的旅游方式，在大自然中感受最棒的住宿体验！</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096153.png\"/></p><p><strong>四季更替，被大自然环绕的京都るり渓谷中的豪华露营体验——GRAX！</strong></p>\n" +
            "<p>大阪、京都中心地出发约1小时内就可以到达的，被溪谷包围的充满自然风光的京都府南丹市「GRAX PREMIUM CAMP RESORT 京都 るり渓」京都最初的豪华露营设施，开设于2016年6月GRAX\n" +
            "    的魅力在于可以随着四季变换欣赏不同的自然表情。</p><p>\n" +
            "    夏天，绿色的山川带来凉爽，在溪谷中散步是最享受的，秋天枫林浸染，冬天温泉雪景，在溪谷周边徒步就能到达露天温泉和泳池。还可以自己亲手制作料理BBQ晚餐，工作人员会适时地帮忙生炭火做协助。</p><p>\n" +
            "    在大自然的环抱下与家人一起在炉边享用美食，搭配整区的帐篷更有露营的气氛。</p><p><img src=\"http://qimg4.youxiake.com/15590125160.png\"/></p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096152.png\"/></p><h2>清溪沟</h2><p>\n" +
            "    山一重水一重，山山水水又一重，人人都知四面山的巍峨，却少有人知四面山的后山有一个地方叫清溪沟，在这里乘坐少有的摆渡船只，摇摆在清澈见底的湖水上，观赏两岸丹霞美景，山清水秀；沿湖逆流而上，可探秘冷热风分离的魔幻阴阳洞，拜谒矗立在群山沟壑间的神奇“石笋”。</p>\n" +
            "<p>徒步沿着沟前行，两边丹霞地貌各有不同，跨越贵州习水和江津边界，一脚踏两地。欣赏被称作“活化石”之称的桫椤等珍奇树种，入门经典徒步线路，男女老少皆宜。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096166.png\"/></p><h2>大圆洞</h2><p>大圆洞，素有\"秀比天池，美似九寨\"的美称，是驴行暴走玩耍的圣地!&nbsp;\n" +
            "    &nbsp;位于江津区永新镇境内，东南离国家风景名胜区\"四面山\"相望、中山古镇相融，西与四川省合江县相邻，北与白沙镇\"聚奎书院\"相连。</p><p>\n" +
            "    大圆洞，人迹罕至，风景清幽，飞瀑古树，甚至有点九寨的味道。大圆洞，重庆户外圈经典徒步线。很多户外爱好者都把它当做入门之旅，区别于常规两日徒步线的全新走法，适合初驴或老驴拉练。</p><p><img\n" +
            "        src=\"http://qimg4.youxiake.com/15591096167.png\"/></p><p></p><p>已下架线路</p><p>\n" +
            "    山绕清溪水绕城，白云碧嶂画难成。处处楼台藏野色，家家灯火读书声。——《徽州》赵师秀</p><p>&nbsp;<img src=\"http://qimg4.youxiake.com/15543599719.png\"/></p><p>\n" +
            "    黄山，地处皖浙赣三省交界处，古称徽州，是著名徽商的故里，也是徽文化的重要发祥地。境内古迹众多，黄山、皖南古村落西递、宏村最为出名。</p><p>{mdd}1711{/mdd}</p><h2><strong>黄山市、黄山区和黄山风景区的差别很此群澳超\n" +
            "    出我查我先带回家啊哈啥的健康联合会哦哦就怪你看看拷问你先给我</strong></h2><p>黄山市是安徽省省辖市，是一个古老又年轻的新兴旅游城市。黄山市辖屯溪、&nbsp;黄山&nbsp;、徽州&nbsp;3区和歙、&nbsp;休宁&nbsp;、祁门&nbsp;、黟4县。</p>\n" +
            "<p>黄山区是黄山市的三区之一。黄山区含黄山景区全境，中心城区位于景区以北。黄山区包含9个镇、5个乡，它与黄山关系紧密，其中黄山景区北门位于黄山区下辖的耿城镇，东门位于谭家桥镇，南门位于汤口镇，西门位于焦村镇。</p><p>\n" +
            "    这里加入小贴士</p><p><span style=\"font-size: 14px;\">{tips}</span></p>\n" +
            "<ol class=\" list-paddingleft-2\" style=\"list-style-type: decimal;\">\n" +
            "    <li><p>后山云谷索道上，走西海大峡谷谷底的路线2.换乘车站（19元）—25分钟到云谷寺—云谷索道—白鹅岭站—始信峰方向—小梦幻—凤尾松—十八罗汉朝南海—灵芝亭—竖琴松—探海松—始信峰—连理松—黑虎松—北海宾馆—<strong>梦笔生花</strong>—狮林酒店—<strong>清凉台</strong>—<strong>猴子观海</strong>—返回上台阶—右转西海大峡谷方向—大王松—团结松—西海饭店—排云楼宾馆直走—排云亭—西海大峡谷北入口—一环、二环—谷底—西海缆车—天海站—白云宾馆—左转上台阶—<strong>光明顶</strong>—光明顶海拔碑右侧下台阶—天海美食—海心亭—岔路口左转—<strong>鳌鱼峰</strong>—鳌鱼洞—百步云梯—莲花亭—金龟探海—连蕊峰—幸福大道—海豚石—鲤鱼跳龙门—好汉坡桥过桥—送客松—<strong>玉屏楼</strong><strong>（睡佛）</strong>—<strong>迎客松</strong>—陪客松—返回送客松—好汉坡桥底左转—蒲团松—玉屏索道站—玉屏索道—温泉站—景区大巴—20分钟到换乘车站\n" +
            "    </p></li>\n" +
            "    <li><p>test</p></li>\n" +
            "    <li><p>test</p></li>\n" +
            "</ol><p>{/tips}</p><p><span style=\"font-size: 14px;\"></span><br/></p><p>\n" +
            "    而黄山风景区位于安徽省南部黄山市境内，黄山原名“黟山”，后来因为传说轩辕黄帝曾在此炼丹，所以改名为“黄山”。我们平时所说的黄山大多是指这座中华十大名山之一的山岳。</p><h1><strong>交通</strong><br/>\n" +
            "</h1><h2><strong>飞机</strong></h2><p><span style=\"font-size: 16px;\"></span><span style=\"font-size: 14px;\"></span></p><p>\n" +
            "    {tips}</p><p>后山云谷索道上，走西海大峡谷谷底的路线</p><p>\n" +
            "    换乘车站（19元）—25分钟到云谷寺—云谷索道—白鹅岭站—始信峰方向—小梦幻—凤尾松—十八罗汉朝南海—灵芝亭—竖琴松—探海松—始信峰—连理松—黑虎松—北海宾馆—梦笔生花—狮林酒店—清凉台—猴子观海—返回上台阶—右转西海大峡谷方向—大王松—团结松—西海饭店—排云楼宾馆直走—排云亭—西海大峡谷北入口—一环、二环—谷底—西海缆车—天海站—白云宾馆—左转上台阶—光明顶—光明顶海拔碑右侧下台阶—天海美食—海心亭—岔路口左转—鳌鱼峰—鳌鱼洞—百步云梯—莲花亭—金龟探海—连蕊峰—幸福大道—海豚石—鲤鱼跳龙门—好汉坡桥过桥—送客松—玉屏楼（睡佛）—迎客松—陪客松—返回送客松—好汉坡桥底左转—蒲团松—玉屏索道站—玉屏索道—温泉站—景区大巴—20分钟到换乘车站</p>\n" +
            "<p>{/tips}</p><p><br/></p><p>黄山屯溪国际机场</p><p>地址：黄山市屯溪区奕棋镇龙井8号</p><p>到屯溪老街约5km，打车约20元；到汤口约60km打车约150元。</p><p>\n" +
            "    公交线路：18路/19路</p><p>机场大巴：机场—市区<br/>运行时间：随航班运行<br/>运行路线：机场（起点站）→国际大酒店→民航售票处→一马路[延安路]→二马路[延安路]→三马路[延安路]→横江桥[西海路]→商贸城→昱中花园（终点站）原路返回\n" +
            "</p><p><img src=\"http://qimg4.youxiake.com/15543599715.png\"/></p><p class='tet'><br/></p>";

    @Test
    public void setTest() {
        String str = "</p><p><img src=\"http://qimg4.youxiake.com/15543599715.png\"/></p><p ><br/></p>";
        Pattern patterns = compile("<([^>]*)?>");
        Matcher matcher = patterns.matcher(str);
        List<String> list = new LinkedList<String>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        for (String tag : list) {
            if (!(tag.contains("<br/") || tag.contains("</") || tag.contains("class"))) {
                log.info("处理前:{}", tag);
                String t = new String(tag);

                t = t.replaceAll("\\W", " ");
                t = t.trim().split(" ")[0];
                String target = ">";
                if (tag.contains("/>")) {
                    target = "/>";
                }
                t = " class='" + t + "' " + target;
                tag = tag.replace(target, t);
                log.info("处理后:{}", tag);
            }
        }
    }

    //(<p><span style="font-size: 14px;">)?
    @Test
    public void setTest2() {
        String str = "<span style=\"font-size: 14px;\">{tips}</span></p><p style=\"font-size: 14px;\">请输入小贴士内容</p><p>{/tips}</p>";
        Pattern patterns = compile("(<p><span style=\"font-size: 14px;\">)?\\{tips\\}(.*?)\\{/tips\\}(</p>)?");
        Matcher matcher = patterns.matcher(str);
        List<String> list = new LinkedList<String>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        for (String tag : list) {
            System.out.println(tag);
            if (!(tag.contains("<br/") || tag.contains("</") || tag.contains("class"))) {
                log.info("处理前:{}", tag);
                String t = new String(tag);

                t = t.replaceAll("\\W", " ");
                t = t.trim().split(" ")[0];
                String target = ">";
                if (tag.contains("/>")) {
                    target = "/>";
                }
                t = " class='" + t + "' " + target;

                tag = tag.replace(target, t);
                log.info("处理后:{}", tag);
            }
        }
    }


    @Test
    public void setTest3() {
        String str = "http://qimg5.youxiake.com/app/201908/16/origin/video_img/NjY1MDg0.jpg?imageView2/0/w/400/q/90";

        str = str.replaceAll("\\?imageView(.*)", "");
        System.out.println(str);

    }

    @Test
    public void test4() {
        String str = "\uD83D\uDC4D\uD83D\uDCAA\uD83C\uDFFB\uD83D\uDE02\uD83D\uDE07";
        System.out.println(str.substring(0, 1));
        str.codePoints().forEach(System.out::println);
        String intStreamToString = str.codePoints().skip(0).limit(100).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(intStreamToString);
        System.out.println(subPointString(1, -1, str));
    }

    public static String subPointString(Integer beginIndex, Integer length, String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        IntStream intStream = str.codePoints();
        if (beginIndex != null && beginIndex > 0) {
            intStream = intStream.skip(beginIndex);
        }
        if (length != null && length > 0) {
            intStream = intStream.limit(length);
        }
        return intStream.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    @Test
    public void bitSet() {
        BitSet bitSet = new BitSet(2);
        bitSet.set(18);
    }

    @Test
    public void classTest() {
        List<Class<?>> primitiveTypes = new ArrayList<>(32);

        Collections.addAll(primitiveTypes, boolean[].class, byte[].class, char[].class,
                double[].class, float[].class, int[].class, long[].class, short[].class, Boolean.class, boolean.class, Boolean[].class, Node[].class);
        primitiveTypes.add(void.class);
        for (Class<?> primitiveType : primitiveTypes) {
            System.out.println(primitiveType.getName() + "  " + primitiveType);
        }
    }

    @Test
    public void testSplit() {
        String st = "，美丽，好看，1，，";
        String[] split = st.split("，");
        System.out.println(split.length);
        st = "，美丽，好看，1，，1";
        split = st.split("，");
        System.out.println(split.length);
    }

    class HashCode {
        private Integer i;

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }

        HashCode() {

        }

        public HashCode(Integer i) {
            this.i = i;
        }
    }

    @Test
    public void testHashCode() {
        for (int i = 0; i < 10; i++) {
            HashCode hashCode = new HashCode();
//            hashCode.setI(1);
            System.out.println(hashCode.hashCode());
        }
        B b = new B();
        b.a();
    }

    abstract class A {
        public void a() {
        }
    }

    class B extends A {

    }

    @Test
    public void testboolean(){
        DecimalFormat format = new DecimalFormat("##.#%") ;
        //format.setMinimumIntegerDigits(1);

       // format.setRoundingMode(RoundingMode.HALF_UP); //设置舍入模式
        double d = 0.999566;
        String percent = format.format(d);
        percent = percent.replace(".0%","%").replace("100%","99.9%");
    }
    @Test
    public void testa(){

    }
}
