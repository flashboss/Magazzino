package it.vige.magazzino.test.hexcodes;

import static it.vige.magazzino.test.Utils.toHexString;
import it.vige.magazzino.test.operation.ImageOperation;

import org.junit.Assert;
import org.junit.Test;

public class HexImageTest {

	static final String[] hexCodes = { "474946383961e2006900e67f0034648dcddae588a6c6a9acaf5b82a388a4bc64b1d4ef5e66b5c7d7154d7afffffffcc9173b6991d9e1e8648aaa9ab2c81a517c003d6e8fd400e7cf964a759af9d558a4b9cc839bae517b9eaac3d90944747194b392abc2285b85eea6ac97b7d2255983c2c4c6557da0e0e7ede4b061c5d4e1a2bdd4bad6a334a4ca678caceabcc2064272f6c8cbee9096d5d8dc406d94e8edf16e92b37c9bb5026fc796999b4d789c437096225781c0cedb7599bbb9cddf4672988374ba799cbdeaf1f40099bab6c6c7b5dd6e7193aef9f9faf0f3f594c3d45f85a6f9dfe12f6189ee777e6a8eb06d90adf3f5f8e39c327598b6f6f8f92c5e8780a1bea5db36f7e8d16187a86489a88eaecc85b7d2ddb3bcad7277b8bbbdbfb8dc0f48777196b74874982055808a97a2978bc51e547ef8fbfa114a78ba8190ada9d1698da0e08e12f9edefed2e388c8f91de8703ee424badc0d1fafbfbfbf5ed0d46767797b2a0a2a55880a2f4f8f862889da1c9e0cdccbbd7c0c9ced0d4add0e5fcfefcaaa7cfc696a4ffffff21f9040100007f002c00000000e20069000007ff807f82838485868788898a8b8c8d8e8f90919293949596978c0a9a9b9c9a989fa0a1a2a3a4989d6f9d9ba5abacadaea2a99d879c7c0ab57ca8b2afbbbcbdbdb1b5a9849db87cc60aa8c19ebecccdce95a9c1ca9c839bb75b613cda5b4cc8aacfe0e1e2c3b4b6de6fc7df7fb4d9daee7dddeae3f3f4bbd69a7b5d33fb5d23dedf9a6a6d714790c7965c0aea295c48ea9e0214fb22eebb636e59c03705098649c7b0a347689bde189048720fc2849c3212ec86eaa3cb9799cc8d204933a0275ba854ba83210fa64f4c11820a1d4ab4a8d1a31134dca87161244d891f6478e990808c1806292ce8e411c68d9c1a00a0404142168059060018a84dcbc0c68bb76eff6dec90bb83c28e1d3528d4c8cb776f0d115544e4f5b27787171b35e6be70fbf62d03c76bd79645d241ac58103730dff8f2458c180862124048409a34192e5ce2a48e13678506d72b5644881d9436d2dbb873238dad41439cdeaf65cf8e1007048607549e4aec510043070864be0020c0a18f4e330f9678094b16c9d9b36a1f2f5e6ca3bc97bb14d2efc580810286f57fedd4c06024051df67fdfa7c750b73cddb9e5bdf058786999e51d00485806450720608619679d41202169a39161616aa9f9d61b6cb4c9269c6e208658946c1a70918018205406c21755bd369b061d88f00044caedc31c06488c26060014c8e1463b1ab9110501361888c45a6ca9d5d8ff0be819a6de7cf309961f7b22b047c719228840450a045059257bf80d761e0572c565435b8e817760770956d6c10d70c2c9d9661396762119acfda6616c1d0e47d4872206fa27710980c0c00e22106087085e300002045cc416c7521cf450e33e57340745686274f0020132b861064166b8c1411518a0a56a78e18d571e7a7cb917e57b55d221c27d74e49ae819041070c612b6de4a40958982a95f62761936d70ee40d18998108262856070c369899679ed5595555a7a1a6e7867cd6e6a7b882965b5b1c1020514315057060c1030f4491025890f6268617727c70e90c26c8e10514a0a188840d46c8f080165b20e0c603329c81c199aa022020796fdde5a4ff5f5546496cadb6e64a0701b9da7186110408b101c8bdf66aab111b6340c77c7a5170de5d4cbae56cc40c78d75d07942d58599cd96a6be1d0aae5f95a707d020aa8b920ba060100745c50840128a040b501160cc98018a93d4d850518d4d803074630d08118734221b10829c820c3050ddbf1b0b3120be85f5d76e995719618dc9a25c81f939c320154f4eac005843b1183037418e180118d7fdcb1adc3beb7176132dbf5ea626d0d68d6e7068a9560836f6ef61968128ec66db719b616dc70c2d9c674a02b24808408051481c20fbcf7fec31d05d00100d7647480af09c9d194835760adf845669549ec85cb5926f656924b5a6c1761eb819968ae280b4e05ffc9851b61be034bc8b1a50cea1b414515e313f0f8e020679971cc5ea477975ce34dcc2a5a070a0b1452f4a6373def0bdab2530230a4270e7948761f5adaec8cc2851b78416abbf35def5050840bd4802a56b9dd052c200089a0e00a169083085e304038914e74ad12d05a1c431eed19a62f556219e5044700f309ce01e3db12151c803e19286109327002111d90022032717c24731fcaaa871ffc1d867fcc3a930c57054001facc5a72ca96684c33b4d500075c49138a0427f8a70400a00a16c8a0067de786146ccd423700000684c001377ce00326b000078420829c558b8095515b77c03320b8b8a52e635a8ffd7205b9c0f5b057ee23621397b8c4255c40ff091b28c00688b8844d6ed2013da442e13ce63730ed256635b0c179020431567dce3bd3a216e96ee0991ba44e7564444d868ee6270f918b8dbb81c00b64508439ceb10852b9c1691290c71d100089ec5b02011a4519cca8885a630960811ab998b9cc4c2f92e458afaa403e763ace014a48410ae209cf782a41094290410cba500027c433064b20623c55c94477f6f06380138ce5f282173265b17f04022059a6b5a001a60845014be08558a3a1e020ad98c8448a06be50830218c0991a340007440085d39021015fe80000e45283c3e4ec8b040ca7ce0ec4455a96c72e7c51689610fa31f8b96f889c6ca23defc9d4b739c109fdbce71298aa847aca138850ff2499dfece7caf6e84f730f45525ad8944b6adda0749f091a74aac2053ce9693549935d488ba201e3cc08a5be43c10308808405bef44428129d8a1a94539d7a072dd813cf5dcea91ebe4d0e933d841f134bc94407c4e09e31c86c66bb10032708c00951e8e73e9dc059cec63305a5dce4102f49325d85097fe6f4c2611ac3aa3521a8ac848510ea465321bf9aa681201dd75c8752570c70e0a478e59d5ef90a290b9546429df10c611954d145826762762b67deb8673f61458e7085432d13531083793255b31be8827abbe0842b8056003dd8670ef6b901a62ea1bcf47c9c140faa2b28bd07038421ccfe6898a45b26a83b63f9192fd30a9ad2880652de3a1aff9f1e78cce1cee60b1430697279a7521c6debb9104060673843dd45f25462e4bc5be6e64325f0092b8a3e1caf7931bbd9f5ae3707398842a504c0011cf7a00b39606f6695d085fb5a75883ebc64b0161a2bcd95698b04021d2301f8a615855842bc848e4b8d36e1702d6d8d4c5b81328570870dff000832a000081cfce0cfcce979291a8b92e4824eff764c65980c6241e599da2a54a1895570a2122e3be8d28e36073d4034a27b60802be818be3d88828f83dc850dd497d0aa05220f5949ac2aa227404bdae2e7d4c2bd9aee206748785083582434b79ef1814ab3f06c120005023c00b92835c003aa00801b38187562d8ccf3dca4b6c7d480007f1682b295ff2d8766cb2189cf765b14a270016a17e0da17c076149a7ddf799a7703f4a5b48f7b40ee4847fa0351b08215cc2d69490399b45dc0ec699ba8caf2b53657567aad6c1963b3ebb1ea051410811196e0673a34eab6a6dbed1817a89a0d21cd987db2701cc4f00221c411af6e4833c028f4cb3767e6acded9011d96a0630b98fce428b7801b56ce723700c10d0a7bb9c2540e2f01f4d309e0c6ac7a713cee6947a10702f8b900ae208007045d00f0fd79a2730c6ece0e999ef5361fca3ec6b785e60f4070814c786c40013ae4f3027300831c0293b315610b81ad1ee6c38d093b0baf800c50e8910970dd3b03645c040078d418dd8c3617dea0ba2f108110fc4078ff2ca8e0f07938bce217bff8c4375e052c68400070800041722089e93db4d2c9fdf32808200c1c1080154cc001d2235de89d4ff4d24b4b557aef57705f824f5eb8fe50ffa1e5055e18f90568508039cc610d6010c10e0050e56c7d8642c1fc568726dc7630972b0eb4a6c0121ee086a919c00019e0800a895f2709f5f2e367a556c8cf508036a8e1fce84fbffad7cffef31f20091e70010e041905cc77e1e7884e3ad239e079a40bc00457f00156c06302d007618074e6f663eaa55984365e47153fae55457e81755927435c470532000634800021000683740635c0005010279cc1607fb54009d05168541b72e57c4de321d00705814772ed1205426004c3f7ff1cbc056ccfe342d455190c5003167700ed578446b87e6de00101602a0520034006749d5700fe170544277a565004a337805a3880fc67004a1764f3a559f27455587549bdf225969337cbe21810f316d7e48173a0050830070f40034b80012c74560f92650f66271c75342b482e2ea81bb1331bd0f70548807b30f30261e14b2086367d0727e0242d0c80013280004478849a78844910001930804207740478745b6805800448eaa66efe177a41b77941b64f84464f29a04a29f3317d5345c942178e54263680016720070300060f300034f00060b004219877a5b36013727c65244cc0016b5f364110e71ba2f10584e54bbcc571a0012199d1201525160c40ff0705800399b889e8c87e495002a69274fde77f5a2880eaf60177600226f04757a08a03e87f90a668af188bf2a44a9bb664ee913fc9823732638135600472a0813037071f1882a9662dcf637ca4717c0cf787cc371b6c473b2b10077fa565bf3161ac811a17621abc852dbc74567fe733d1c200041005e6988e34b97e1ea0031fc07fa1d50743978aa6688a7f6402f578077f848fa638853aa974a4f58f559502e6133f2a9331ff05252ca618d8756c2900066b30000f50006bb006db2482a4b3229b017ea1511a118646e1321483c8961a400610000247224348e04b78c22767941a43d3713df88d8874609f528ee7589335b98e19f000fb688545698f1960ff8f2690017b90018d599446a98505c88fad28033d80739425453e2439b77254407486edb16f8d510323270460000617200404104b08422d89547c0af752ad138d5e362e6d698810d001955805f924074b600460f11ca9e11abf214c7bd78d10e0977126978b1498334998d4d90008f0003c297a41f9980880003aa0037b009e01f09d19508ff75894f3f8017d104880f42e02187a51e00472900281267584531f4bb001cf169f706332f164045e857b5c175946154b8865198405899d817cdcf21b23f970ba49885cb0237bd4472770a14060010590025e8004628027bda11a6d857c015396d093226d7220d23998e8c8022eba782ce0012caa7e79a0032660ff0588f9008ff99d01d0a33d2a7901207925a00319800042698f1f50048cd99d3830a43a40790ab3301c5000f1358b7e865a32706d1cf00027e706166002f0d2841b90028c621e84413d7681583d334006549111124c66d41a5e0641b8f1915741000500044120017ccaa752700216200318303c6410a226e9607c878dd0535d63311604239884390630d000508a032e000369607eed970738708f3a5aa425100023300230e002a6ea023e0003a4daa3431a998d790719a003a12aaa2370aaa73a020150029537a57270591b90a5eee2064d1a002ea007c68a03b98a030b53006327027a61318c714b6311679b61766833211582275cd0515da6461546574f43ff1d27d0a7e4eaa74010053590236e6592bf041a72828d2902028665162b4a984fb0840fb0a5d4170044d00245a80225900166f098a15aab5a40035f99b06b40035a80aa23d00025109e90a9033d3a027a70b00aabb071e802baaaa105506dee62a9213000bf97b15f390721e00295170581e1050252204642193d935b98116ca7a33a20d9706ad976de6a142bc0051d80010530aee55aae5250047260036b862126f24b90f871392597d18220f55a9344307f05306dee520230c00245c8023820991910a42e80b0269bb103d000aa1a007b30ab231002667bb6682b7fa6b2a5085002182bb768abac1b4a2688153a13259b875482d7ca5b5bc6511fc97c1d99ff4c0cb004402005455bb441f0004600005fd02d2179222166a2a4c3339341b57600a93539044bc801a6bbaf4ce001edd7060da0030830b623a0057a7bb621e003a34aab253bbb261b02ba8a0038a007b9abbb09bb815ef19a5d142d3b4340147940d1a53a6d9596e0c291df4a5794b2a7915bb44020073be06bc2b483672789b24951b82431788a0349409843000393e7062180036a9ba9eda784b21aa4c12bbc5fa9054ce003a95abff6fb957a207923d0bf678b030f40486ab15307b620a3532d1d808d08141a1362214513a20e24bd2ec805b56601907bbde51a041c800150900020f95219d519d5cac09521b30612982530a39ae8a22ca0782eea019abaff7e0730022590c3233000029cb15af0066f10023d9cb13430066f20bb439cb0738000437226934116290c4e29f28d15e919147221db8ab810da966480046700041c2cb97b15c223da8c9d0102c1365d6ee2b93a6303e57bbed4998e079006421abb49acb07af006fcfb950c1b022180c467ab0744a0b77d3cb27a0b04054005076724c4471671c6200684c69c81ad4403a2b869c1b841060c70060810c6466b01950b29dbd28dc1e677b229973c33169fe3c6a21bc79ad80269e00392d7000d10b7268bb221b0c7097b04723b0744f00643c0044f0003b69cb05ad000723b004330044f40044350cb673b00a752038c38b58d2a7e0b928d24c860dc821a25b2ffb37275145dbc049decc97d2a05a09c236d05979aeb9c90ccc63c33b5e3d8caae6c8407c0024330aa3000c844dc00c00cc42ea0b73c4cbb404c043000034ca007d11cd0b4bbcc0f3b024c20c4b77c01f39233d082bc94f1ce9c6165a9231a87db40af13716016074860046e60ce7deac174d05743a3b99f017e9e8b2011735d46c001d359cfaffc064f3004c59cb02ef00475500746ac000cddc30d10cc077dd07ebcd47efcbf724b0323300444a0cf2ec0d47f4cb23260041470c0085651baa4bc0cd2bc1dbd5166a4b83d6b14c521021c60bde67c0205a066dcc27127e24272264bc6f21e2efb1831d9c2388d8e49300453d0cb4350074420d4c6a0c7493cff007a00034ff0046f300643a0d36940d5b3ab0578000344b0d3cbcccc38ec061b2a02672265290a4e5f4d9185ab6519e91b239946c2455c6260033240b4e65c472fc0bd657c2273e21dbd5805e9730170339fdb2417a17bd37d7d842a20d1262bc83e10d463700c459dc43480b27a10d563f0b0413ac8c2eb7b290b03c13c02042c035440012f0b2d13f545ba2427c2f6a6583cc13b3bbd44613bb6c6d61c5c02cda1aeec0a5d7096168257002a07a582240776903f5440cfe818d9534dcba47a04325a846d30d0268bd9fa4b04cd8d0c017cc7264b0303700493da006f9bc4c7bc841770067e6b60b795d1d502b5702246c104a20d949b83a80114b7041620ffdf457b0294cb002cb2ce17d99ccfa33635b004657000ef27e442de066520075d32e07c5d93d5cd8e2cf7bb51edc2e7970572dbcc3e50d8cd8d0b6f90cc0edec373f0b0b4accbbaab05f327077410da089cd1977148841562685721cfcb512d1e71b7f17620b0037230e31b4cae520004bb66035461926d953a0874560c803b65d07e65702a74900204be89445002f99a6dd76601083002475084547eb6567ee54f700c5b1ecd4cc0cf02fce5b4dc005d5eea08c0012920deb7143ab349580f22d611bc65dceae2ee5d1b09d001367006ee7202412005411004585b3654e1573a4eca6200059bcc01feca7e2d90427fc6014b4e9323e0d942a059596a020130ff0445a8b74f90aacbedd8c590ccc87004792bc05a60ddb59aeaf6ab05426207e31db33ec3c06074ada7631a4cab96228d1b1a404d7a8444a7db8455d03cd05122cf2b1aa1115d20f00255f000cfbe7e2d800005904f16d000526e84d7ae7da8a59f5bfb0452bee90f6ed0575e07c50003c9fcd84cc004cf6cc8fd7b04b41ca498ea025ae0ee7a1b021b3a7c18ad53f52eeb2b09c16bb551abbdc53cbb1b5c00016ab3037d2318457236225c22aab197a8c34bbe094770bc7e4970edf082034c409d3e50021e4be916a0032390069a2eb7a8aabf866dc4814dd063b0f24c4004714f040d70b13dadb02170e01c7ed0a93aa97a408cb3ebe7c828516cd24d6605ff7ed7e27d1fc6565c46f4bb99142692c66ff219965c92517ff4d08536cc5e051ca002ed7700cfdca423e0f9e8ebdd2de7bb477df5582fb75a5007b62bd463e0d8a4eed340acd34f90bf93fad04fd000baaccc6f30f74460bbc4daa447add0ac6f014230a831bb33ba64566eba607612e7d1a801d22b882fe81bce4546725e22d9ef7d73328e1c900745980469b0cc4750c33409cc7a3faa4ca00219af062da0b710bedc4c30066ddfbf0360dd3dcadd280f086b828384855a0138086e2523432e8583211672350c480000504848501d9e201da037205f2062a71062096409715c5c1ab12bb3b311b5b62b11babbbcbdb6716462371d50c5a409ab71ae5caca9ff105f625f481832386d6ad8d9da0707daded8d7dfe2e3e347039082734c4c7544633073e8f26b343e230d0101236f44f3e8f58810e068f4089d16374b3030c0040500920e0fa1809008eacb971bc220688480ec952b0d7156c8a295cb96af93bb560093c6a086080276e8d47801000447323859251093ea14142f42dc242147545b8ba248bf1d18d2601e8d102ea668f137d52011182346301952d59fa127f81a1049730e5d88074b28605abb4913c44f204c41e3a911192b32ca4086a4858b57499429e340806283809c020f2c587850408e111b486eace2828ca3c668201850e1802529b924473d7bf6f0e44959aff342c0993727c4910621e2c96b2aef69ff0317b16be32860e4c542879b88b9f534ea864568756fe28415479606beb5fe02dec50502000c721ee09950a17b8509781ec8c1507372b39e5f3a00a821074137d1de8e84864f34098c062360d0402d6f00913721f0378f1663c826202407c921c20b9700c789709d7852ca0dcf20970047afe005cb48b9dce2d77411c491000022141042050ba4a8628a130051001d0c48b6ca8517f27403140c1851801ef465d3c213f3f5f84d120d94804309b71d58c8083ef8008f9284d0400413fa4139c81c3870908217bf71e2a027a19012d7713d29e74a73cd8d844b8720a6940014181430c18a74a658c16ee4a952dd85a9407303003b2cf1401ee18896c43a410aff094e0b0de89081091920d0400306f2370756f9e057a98073b8504209011404e51c7a3c20840836ace550436012175771a8d4b50a19af7091e6736cb6e94b1c5f7821871e75065b01107278d1c14d7c46330a8e350831e87b497910c0a789f6d8460b9e22b0d8030f6c814000b0f1f7140cf9eca1c31ee076c5df21382c6601022ea8eb150d5ae0201e011430608925c045184a9817dd40e146ca79d41c491d7ad8e60a640060840528065b67050f5001802a6470b4530216415498101c208005b4e21cd0821e6e70fb801fdc1c9084cb2dc70c73cba0a9e04240263cc081005608f0811b0820394208fbfd13c27d0138fa2802192009dba68590fa69ca0544ff11050716e0106fd1f2b486320742185109bf9704d789311d9432d7c01a2790e1adb842a76b4a62bc20c39c12078b871c3b8060d74620087c23122d5521c3038be481450b8cb7e001167a28c24114726c7098056ee0a0c7a7d37e5a82a79d4f3b6d226e98f0811505546db5003c5bb0850ee0de835b08b4bbd000a625306dfaee91828a9f1eb3d3ae753e89302683101bc420c3d5ef82aa871e21001142bd7a20e2060717a420936f986c920944c584392634736dd491ad793d17b74925e58a92062060c001de79d3390107184051d946a85c341100361041150e3300c5b8e1808b999c1c52608406a640094e8842013840410ea84c65eeba20b776160519f4ff20073db05a14786605cc2140079c9bd4a446002e1d38ca04a6b382153ef081c46c0168413bd2a77090086d3d400051d8400aaa4005232c4108729060624c08340472a0003258020130608354a9ea126e79c82704779158d18856e80bc9fada67923202260e507858c4eab7228a19010010d0496576728a514084013ba0001d8c8844241e910a5520001d44e0851a6000030470400a1e7844e4c9e1918f94812425290719c8c1091be8c206329903108a500016c45c0632e042147eca858fa2e1e94e67059dc9f0740ff880624cf780560aa0003d90430c8868843d12c0085548c112962049ab15e0021d948310aa20132fec405fbfe95e5bb4280a8159c419ffb24a8632d08430f79911305c00401580c0c68959a00a7004c68cf8278c524ca4210c78c10e6a40811ad073071898890de2698317d8a09e86c4001d064a8082da81005530c21017b904453a20064a50420c26dac910ae0e9489f9000c1f354a0b648086320ca91576c63ad6196084b7ac5ad524b90125380090bf24c04b084a87823610905410a408ec59456802a04b9a08ce2700769cf2d9058ce9531319e7a60b2e20a10a6e28279d2ae0068b41801994b1cc650226b04e786f2d3d85e6425e4056b2eee09f5ea8e7215d8201110cf425bfa48203e6aa48253c70a231e841088118859e09e0017d00ec0360285291fe907523344008f5da4919246f812e2dffa211625a53998ae09018506b3eeb49811d9c555f34d19734b1f8a008bdca38cea0d1f96ed54d3649a74d5c48a31bd628558ad9010910d0003390b1bf53606618a1d0c44fd932dc9f82d69f67a54067edd9d6b612b4a03695ab5c1d10d1884e740339f0e00847d8b391d6d20d66b0602b393052bf8a90b13de8827a210ad11440f4a546286241df4a87e6d620ad14a8627e9fb94fb21af78a6d395b5c02679c8c6895b7b040df2c9e53c6bf2c753af0130107685bcefb61a0037859c63af964118179021396289baad612cf7892b58a7954ae3d45c0629a42b7810aa5c222edba49f5e62082db655d01503743bff6ec0a89e52b7ad59b5e885ad7ae308d2974ff07d9567bda739e36386b3fcbea1b7d11ae6c6d798b285e851cba688c15e8535f741a9c92b969400c3bb800fda48a0719d8e00b64d0cb6e6bc493687c411314019f701b34dc2a33609f51d62f0530ebdc9aba35a6f19dee8cababde2ee8d598abbbc25fad30c2020091af51606c279db0dee4b537a22970c04d97dce2b602949effdc813fc95a62d13a449ac59008318601ab2fa4a23238694552bbd9976f2e6c44678098541730010b188101ad0049c6c41097f0c53a140203c5d98ad1a02b2fc4c42696678a9d7cd9e6eab48104a082b883b9c8bb2ac1c615cd748e2dc0d74bebf5dd158d41273799c97253b70a0985ae4cdf7a5927df53b99fa5323409d7ffbd4c0475c0a16076340876545a81c4392431e36b1716071050e0027818b61e64e005c9b882631d88673d2f4b019a74e2551db8d13b37d1a06b43d3376655b572d7cae2e6ceb4a60db48311e8fa40f79e7b939dcc4117acd60350aa1bbd1f4c6f2763b05e23c720d4a17ee94b0549751677dbc99d75e6aa590d73e262196dd0eea2adb339195bc5226eb748982e260e220d24000904985f39a7f022dc6635e41830821c2e903a4b5601559910c5bfbc1ad4069955c59ead4172010ad043bab5a60485f14b65fc40ebe2f5835d70427aafa6f42874b20b41173ad327ba844f2f6191f005f77c0759f37cda53b9fe9c32ab45fb9bdf50fbe4291ff05ce8722130ffe2e5ec08eb35535112073100603319975805f05000025cac1900a0400a108380ede0010f08604c0a14f23d0f1343cf00f027055e22637c1ba1e6f44c7f3ed72fd0f9225adc32a6ae7b4f2fd12e282193a0bf65a3f7df69fb4b74d1a09602d20563543050fcb6590067035e1065acb64fb4b71685877b6182115e661947f50a0f2712c1b7761c387c29c105c6470017e006dcd146c5260330026719c3002220036ea0052480066c30836cd00424101e3240000c223ead72092f500376b0047b37414f7401321003544072a78659563753827453d2156a2ee55e78c5749b2441391003f8377a5578640cb54831165fbd84736e4568f5845fce3465fd24565dc2ff7201867be4830aaa65171828461be86b1e98122b00824850032970018883003e740176e005b88513d6512238d00434d8883438016e50007660093ca8097f26027f981841b343da32395224022ad6788e07579565537115758a646412a585022003abc85e12555d4f17038ab448e2b673b8284804f5128ee76469e505cea46a53265620364dcf06051e460a16e16576e136cb912611e721de9487bb202299e1057440056790027620023b000014d21c10d00118100521c0888e988e24800028c800c8e83f0db10304e02c0890072d002d6d90041ef035729084aee705ae378a06f88436c54b0a550576e57329b00156b34846264ce5165173156a51086308f5ff1256f756ae97473bb08055247bc4f8536e7836df373ec8914dcb114668b7261f428d28b1873b01026523117a320bc5670342708ee9b8936c4002402004141019a5e03146308223430e6de001ed22030e505f4be8124d3850bd54903955442e55578bb40102b0011359793c277f93276a93e50054f74b064873fdc6915b2770d1c41011211c5c761c5e4487b4b24d1ca2302ea92b0ba60c58250b2641064840070f40023cc99324c001c7963677f6022210056ee00149710079600132400508786a01255083345ff205634a105f0e707a0f75842f857a74257589066e0d948d86566a5089757914650be84f6c38629c103e71411c01834d96016675993ed138ff6679399c6b07012f20072150983c89060820045e0005ccb61e72600190e9196d502a72802f1d494f98c97e06d84b93a59a0d045f1b1045d33579722586e2668ae10979ac37456bb55969755630576234016210f86cc4705a18911c6d53761f7197d2489c041a0737102713a09c3cc97c1810193892020fd019f07100bbb17d52a65cf81590dee6568fa77a626804c8b39e73b59ebf3459253a5f18a98b6db55374706a14008c9e4565ac565cd5866770116dd1c08c0d6730c0c7176b378d049a8768440016808e0aea8824f000ce77037d78015b5028a28105934000f479566888994ce65c22707e52099e1bb04c95a57a7b0479fbf67851c962cce56481b6ff6a0ec83d23e6106fd12a04b670723823396176ac151d038a87413a374e05553278a4487a4e0ca01e46c001d5491f6db01b5450727f4656c1d8912f4a68eb575f852695c36468eea9a90369759815503b755f2fea590c584565d596af769b274711d6b47baa7517b49261d02837ee83977dca54e194026e20a8e9d804840a052fb0040800a5f09107683113fcd44f56ea4c93ea6f6c358a534400a727536d45ad97556ae887a64f09a31da986a0655c5df225b23681183176ff09ab1ef17062e6a3d2c176b7eaa7e2040481caab3348021660315090665aa0286ad0026eb0016333566515659e35aa85a45c3bc584d53a4484d66dbd9899afc79d67389fa40aff737e565c0bd110b14692a7158716a81c3861973d3a667a5a66ef9a875cd0017650a4f44a838749009c50030520a14292046e2003dcd767c7f591a49a78293668af4707cc94590165483337689c755f8ad7599ee5591e599f0fa82a1a8b9b03661cd7e4455f362bdbb46b2be9ae274b8df053031c40982dcb065a500035a009324bb33d7200ecc87d7c06ae32aaac25178c07ab470e008ac0c859f825a9009786557a62abe672346a8cb8096da44081046381395197cc21a0c2f9b57dba02c62903c9d9b2cc29042f300a6bcbaf6e4b09ab021cb5775cd8a6860a186583263656da9167d5ba314a9fb2d75f177b6da3759bc2e10912310ae5b3b8bc758167a2ffaed1c83e921b017f50bcc67bbcc89bbcc8ab00cccbbc462aa813f006cdab007ca0002ce0b9d5cbbccabbbddcdbbddefbbde01bbee23bbee45bbee63bbd7c0007f3aaa04dc007d9cbbcd57b04fcea01d2abbde67bbff89bbffabbbffc2bbecd5bbd53b0be3cd9047040bdd32bbd6f40aca2710406ac00fdfbc0101cc1122cc107ac0053f0bcea0807d2fbbecc2bbdd592146de0becd3bc1245cc2267cc2c95bc17c3001185c832c90bdf5ebc0ff0b070a8c141ec0c1289cc33abcc3f9abc2e93b051340025330050a50bfef5bbc1d5cc42a401f0710c30eccc3501cc552acbcd3fbbf1b6cc4556cbce8cb071f3c0e079006453cc2533cc664bcc3558cc5619c79c5c78bbe6fd0c5de70001a3cbd653cc7746cc2557cc7629cc2f04bbd2c50c33eb2c1725cc7823cc80f8cc74fccbd775cbd2a7000507a001e9006d97bc4843cc9946cc6ff4bbd08bcc76f50bd229cc795fcc9a04cc1890cbfefcbc1871ccaa89ccafb7bc79b6cc0a67ccaaa1ccbb24cbe867cc7b37ccbf71b08003b" };

	@Test
	public void decode() throws Exception {
		ImageOperation imageOperation = new ImageOperation();
		String result = toHexString(imageOperation.create());
		System.out.println(result);
		Assert.assertEquals(result, hexCodes[0]);
	}
}
