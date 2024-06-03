package com.tenke.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tenke.voice.tts.TTSManager;

public class TouziActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView mTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_text);
        TextView textView = findViewById(R.id.content);
        textView.setText("2024-05-13\t0xGen\tAI整合DeFi平台\t未透露\t美元\t65000\n" +
                "2024-05-13\tSubtle Medical\tAI医疗成像解决方案提供商\t近千万\t美元\t6500\n" +
                "2024-05-11\t亿铸科技\tAI大算力芯片公司\t1亿\t人民币\t10000\n" +
                "2024-05-10\t生数科技\t多模态生成式大模型与应用产品开发商\t未透露\t人民币\t10000\n" +
                "2024-05-10\tDatology AI\tAI大模型训练服务商\t4600万\t美元\t29900\n" +
                "2024-05-10\t国关智能\t人工智能行业解决方案提供商\t未透露\t人民币\t10000\n" +
                "2024-05-10\tDeepX\t人工智能芯片研发商\t8000万\t美元\t52000\n" +
                "2024-05-09\tPanax\t人工智能现金流管理平台提供商\t1500万\t美元\t9750\n" +
                "2024-05-09\t超星未来\t边缘侧人工智能芯片提供商\t数亿\t人民币\t30000\n" +
                "2024-05-08\t光魔科技\tAIGC技术服务于创作者\t数千万\t人民币\t3000\n" +
                "2024-05-07\tOpmed.ai\t人工智能优化平台提供商\t1500万\t美元\t9750\n" +
                "2024-05-07\tTekst\t人工智能平台\t70万\t欧元\t546\n" +
                "2024-05-07\t主线科技\tL4自动驾驶卡车服务提供商\t数亿\t人民币\t30000\n" +
                "2024-05-06\tApex.\t人工智能安全技术研发商\t700万\t美元\t4550\n" +
                "2024-05-04\tSpatial Intelligence\t李飞飞创办的空间智能公司\t千万级\t美元\t6500\n" +
                "2024-04-30\t星元AI\t人工智能+营销系统服务商\t数千万\t人民币\t3000\n" +
                "2024-04-30\tBlaize\t美国AI边缘计算服务提供商\t1.06亿\t美元\t68900\n" +
                "2024-04-28\t火眼消防\t人工智能消防解决方案提供商\t千万级\t人民币\t1000\n" +
                "2024-04-26\t沐言智语\tAIGC产品开发服务商\t1.2亿\t人民币\t12000\n" +
                "2024-04-25\tAugment Code\t人工智能编码辅助公司\t2.27亿\t美元\t147550\n" +
                "2024-04-25\tParloa\t对话式人工智能平台开发商\t6170万\t欧元\t48126\n" +
                "2024-04-25\tCognition\tAI程序员技术研发应用商\t1.75亿\t美元\t113750\n" +
                "2024-04-24\tAirchat\tAI社交媒体应用\t未透露\t美元\t65000\n" +
                "2024-04-24\t爱诗科技\tAIGC视觉多模态算法开发商\t1亿\t人民币\t10000\n" +
                "2024-04-22\t智未来AI\t人工智能解决方案提供商\t500万\t人民币\t500\n" +
                "2024-04-18\tAI Squared\t低代码 AI 集成平台\t1380万\t美元\t8970\n" +
                "2024-04-18\tFYLD\t人工智能现场工作执行平台提供商\t1200万\t英镑\t10560\n" +
                "2024-04-17\t灵汐科技\tAI类脑计算芯片研发服务商\t未透露\t人民币\t3000\n" +
                "2024-04-17\t灵宇宙\tAI智能对话软件\t未透露\t人民币\t2000\n" +
                "2024-04-16\t米粿Al\tAI技术解决方案提供商\t未透露\t人民币\t100\n" +
                "2024-04-16\tG42\t阿联酋AI人工智能技术公司\t15亿\t美元\t975000\n" +
                "2024-04-16\t诺谛智能\t认知与决策人工智能企业\t近亿\t人民币\t10000\n" +
                "2024-04-16\t喔哇宇宙\tAIGC模型创作工具开发商\t千万级\t人民币\t1000\n" +
                "2024-04-15\t催化剂加\t大型全学科语言模型\t千万级\t人民币\t1000\n" +
                "2024-04-12\t瑞莱智慧\tAI行业应用服务提供商\t数亿\t人民币\t30000\n" +
                "2024-04-11\t深势科技\t药物模拟研发平台\t数亿\t人民币\t30000\n" +
                "2024-04-11\t面壁智能\t人工智能大模型加速与应用落地赋能公司\t数亿\t人民币\t30000\n" +
                "2024-04-11\t卡尔曼\t自动驾驶智能装备企业\t数亿\t人民币\t30000\n" +
                "2024-04-11\tsapien.io\tAI数据标签提供商\t500万\t美元\t3250\n" +
                "2024-04-10\tBasemark\t自动驾驶汽车软件研发商\t2200万\t欧元\t17160\n" +
                "2024-04-10\t墨芯人工智能\tAI芯片设计商\t数亿\t人民币\t30000\n" +
                "2024-04-10\tSymbolica\tAI基础模型开发商\t3100万\t美元\t20150\n" +
                "2024-04-10\tGoodGist\t人工智能技术开发商\t100万\t美元\t650\n" +
                "2024-04-10\t昇启科技\t智能化仿真工业软件研发商\t未透露\t人民币\t100\n" +
                "2024-04-09\t适境创新\t人工智能行业应用系统集成服务商\t未透露\t人民币\t100\n" +
                "2024-04-09\t海港城\t抹香鲸APP开发商\t数千万\t人民币\t3000\n" +
                "2024-04-09\t原粒半导体\t创新AI Chiplet供应商\t未透露\t人民币\t100\n" +
                "2024-04-09\t特斯联\t城市级智能物联网平台\t20亿\t人民币\t200000\n" +
                "2024-04-09\t星凡星启\t一站式行业AIGC技术服务提供商\t近亿\t人民币\t10000\n" +
                "2024-04-08\t交互机器\t人工智能应用软件开发商\t未透露\t人民币\t2000\n" +
                "2024-04-08\t云澎科技\t商业化AI人工智能应用产品研发商\t数千万\t人民币\t3000\n" +
                "2024-04-08\t捏Ta\tAI驱动的次文化内容共创社区\t1000万\t人民币\t1000\n" +
                "2024-04-08\tHailo Technologies\t深度学习芯片研发商\t1.2亿\t美元\t78000\n" +
                "2024-04-07\tCelestial AI\t人工智能加速器产品提供商\t1.75亿\t美元\t113750\n" +
                "2024-04-07\t超级崽崽\tAl情感陪伴创新解决方案提供商\t未透露\t人民币\t100\n" +
                "2024-04-07\t幂堂科技\tAI智能拓客产品研发商\t4600万\t人民币\t4600\n" +
                "2024-04-07\t博瀚智能\t人工智能技术提供商\t数千万\t人民币\t3000\n" +
                "2024-04-04\tCHIMER AI\tAI设计平台\t数百万\t人民币\t300\n" +
                "2024-04-03\t探维科技\t激光雷达技术方案提供商\t未透露\t人民币\t3000\n" +
                "2024-04-03\tBrandtech\t生成式AI平台提供商\t1.15亿\t美元\t74750\n");
        TTSManager.getInstance().speak("共拉取到63条投资事件");
    }

}
