package org.trimps.images.analyzer.lsh;

import java.util.HashMap;
import java.util.Map;

public class Feature {
	private Feature() {}
	
	private static Map<Integer, FeatureEnum> featureMap = new HashMap<Integer, FeatureEnum>();
	
	static
	{
		Bust[] bs = Bust.values();
		for(Bust b : bs) {
			b.toString();
		}
		
		Waist[] ws = Waist.values();
		for(Waist b : ws) {
			b.toString();
		}
		
		Hip[] hs = Hip.values();
		for(Hip b : hs) {
			b.toString();
		}
		
		Length[] ls = Length.values();
		for(Length b : ls) {
			b.toString();
		}
	}
	
	private static void addFeature(FeatureEnum feature) {
		featureMap.put(feature.index(), feature);
	}
	
	public static FeatureEnum getByIndex(Integer index) {
		return featureMap.get(index);
	}
	
	public static interface FeatureEnum {
		public int index();
		public String description();
	}
	
	//TODO 根据图像特征值的范围修改
	public static enum Bust implements FeatureEnum {
//		FITS_0(0, "Bust Fits 0"),
//		FITS_1(1, "Bust Fits 1"),
//		FITS_2(2, "Bust Fits 2"),
//		FITS_3(3, "Bust Fits 3"),
//		FITS_4(4, "Bust Fits 4"),
//		FITS_5(5, "Bust Fits 5"),
//		FITS_6(6, "Bust Fits 6"),
//		FITS_7(7, "Bust Fits 7"),
//		FITS_8(8, "Bust Fits 8"),
//		FITS_9(9, "Bust Fits 9"),
//		FITS_10(10, "Bust Fits 10"),
//		FITS_11(11, "Bust Fits 11"),
//		FITS_12(12, "Bust Fits 12"),
//		FITS_13(13, "Bust Fits 13"),
//		FITS_14(14, "Bust Fits 14"),
//		FITS_15(15, "Bust Fits 15"),
//		FITS_16(16, "Bust Fits 16"),
//		FITS_17(17, "Bust Fits 17"),
//		FITS_18(18, "Bust Fits 18"),
//		FITS_19(19, "Bust Fits 19"),
//		FITS_20(20, "Bust Fits 20"),
//		FITS_21(21, "Bust Fits 21"),
//		FITS_22(22, "Bust Fits 22"),
//		FITS_23(23, "Bust Fits 23"),
//		FITS_24(24, "Bust Fits 24"),
//		FITS_25(25, "Bust Fits 25"),
//		FITS_26(26, "Bust Fits 26"),
//		FITS_27(27, "Bust Fits 27"),
//		FITS_28(28, "Bust Fits 28"),
//		FITS_29(29, "Bust Fits 29"),
//		FITS_30(30, "Bust Fits 30"),
//		FITS_31(31, "Bust Fits 31"),
//		FITS_32(32, "Bust Fits 32"),
//		FITS_33(33, "Bust Fits 33"),
//		FITS_34(34, "Bust Fits 34"),
//		FITS_35(35, "Bust Fits 35"),
//		FITS_36(36, "Bust Fits 36"),
//		FITS_37(37, "Bust Fits 37"),
//		FITS_38(38, "Bust Fits 38"),
//		FITS_39(39, "Bust Fits 39"),
//		FITS_40(40, "Bust Fits 40"),
//		FITS_41(41, "Bust Fits 41"),
//		FITS_42(42, "Bust Fits 42"),
//		FITS_43(43, "Bust Fits 43"),
//		FITS_44(44, "Bust Fits 44"),
//		FITS_45(45, "Bust Fits 45"),
//		FITS_46(46, "Bust Fits 46"),
//		FITS_47(47, "Bust Fits 47"),
//		FITS_48(48, "Bust Fits 48"),
//		FITS_49(49, "Bust Fits 49"),
//		FITS_50(50, "Bust Fits 50"),
//		FITS_51(51, "Bust Fits 51"),
//		FITS_52(52, "Bust Fits 52"),
//		FITS_53(53, "Bust Fits 53"),
//		FITS_54(54, "Bust Fits 54"),
//		FITS_55(55, "Bust Fits 55"),
//		FITS_56(56, "Bust Fits 56"),
//		FITS_57(57, "Bust Fits 57"),
//		FITS_58(58, "Bust Fits 58"),
//		FITS_59(59, "Bust Fits 59"),
//		FITS_60(60, "Bust Fits 60"),
//		FITS_61(61, "Bust Fits 61"),
//		FITS_62(62, "Bust Fits 62"),
//		FITS_63(63, "Bust Fits 63"),
//		FITS_64(64, "Bust Fits 64"),
//		FITS_65(65, "Bust Fits 65"),
//		FITS_66(66, "Bust Fits 66"),
//		FITS_67(67, "Bust Fits 67"),
//		FITS_68(68, "Bust Fits 68"),
//		FITS_69(69, "Bust Fits 69"),
//		FITS_70(70, "Bust Fits 70"),
//		FITS_71(71, "Bust Fits 71"),
//		FITS_72(72, "Bust Fits 72"),
//		FITS_73(73, "Bust Fits 73"),
//		FITS_74(74, "Bust Fits 74"),
//		FITS_75(75, "Bust Fits 75"),
//		FITS_76(76, "Bust Fits 76"),
//		FITS_77(77, "Bust Fits 77"),
//		FITS_78(78, "Bust Fits 78"),
//		FITS_79(79, "Bust Fits 79"),
//		FITS_80(80, "Bust Fits 80"),
//		FITS_81(81, "Bust Fits 81"),
//		FITS_82(82, "Bust Fits 82"),
//		FITS_83(83, "Bust Fits 83"),
//		FITS_84(84, "Bust Fits 84");
		
		FITS_32(0, "Bust Fits 32"),
		FITS_33(1, "Bust Fits 33"),
		FITS_34(2, "Bust Fits 34"),
		FITS_35(3, "Bust Fits 35"),
		FITS_36(4, "Bust Fits 36"),
		FITS_37(5, "Bust Fits 37"),
		FITS_38(6, "Bust Fits 38"),
		FITS_39(7, "Bust Fits 39"),
		FITS_40(8, "Bust Fits 40"),
		FITS_41(9, "Bust Fits 41"),
		FITS_42(10, "Bust Fits 42");
		
		
		private int index;
		private String desc;
		private Bust(int index, String desc) {
			this.index = index;
			this.desc = desc;
			addFeature(this);
		}
		
		public int index() {
			return index;
		}
		
		public String description() {
			return desc;
		}
	}
	
	//TODO 根据图像特征值的范围修改
	public static enum Waist implements FeatureEnum {
//		FITS_85(85, "Waist Fits 85"),
//		FITS_86(86, "Waist Fits 86"),
//		FITS_87(87, "Waist Fits 87"),
//		FITS_88(88, "Waist Fits 88"),
//		FITS_89(89, "Waist Fits 89"),
//		FITS_90(90, "Waist Fits 90"),
//		FITS_91(91, "Waist Fits 91"),
//		FITS_92(92, "Waist Fits 92"),
//		FITS_93(93, "Waist Fits 93"),
//		FITS_94(94, "Waist Fits 94"),
//		FITS_95(95, "Waist Fits 95"),
//		FITS_96(96, "Waist Fits 96"),
//		FITS_97(97, "Waist Fits 97"),
//		FITS_98(98, "Waist Fits 98"),
//		FITS_99(99, "Waist Fits 99"),
//		FITS_100(100, "Waist Fits 100"),
//		FITS_101(101, "Waist Fits 101"),
//		FITS_102(102, "Waist Fits 102"),
//		FITS_103(103, "Waist Fits 103"),
//		FITS_104(104, "Waist Fits 104"),
//		FITS_105(105, "Waist Fits 105"),
//		FITS_106(106, "Waist Fits 106"),
//		FITS_107(107, "Waist Fits 107"),
//		FITS_108(108, "Waist Fits 108"),
//		FITS_109(109, "Waist Fits 109"),
//		FITS_110(110, "Waist Fits 110"),
//		FITS_111(111, "Waist Fits 111"),
//		FITS_112(112, "Waist Fits 112"),
//		FITS_113(113, "Waist Fits 113"),
//		FITS_114(114, "Waist Fits 114"),
//		FITS_115(115, "Waist Fits 115"),
//		FITS_116(116, "Waist Fits 116"),
//		FITS_117(117, "Waist Fits 117"),
//		FITS_118(118, "Waist Fits 118"),
//		FITS_119(119, "Waist Fits 119"),
//		FITS_120(120, "Waist Fits 120"),
//		FITS_121(121, "Waist Fits 121"),
//		FITS_122(122, "Waist Fits 122"),
//		FITS_123(123, "Waist Fits 123"),
//		FITS_124(124, "Waist Fits 124"),
//		FITS_125(125, "Waist Fits 125"),
//		FITS_126(126, "Waist Fits 126"),
//		FITS_127(127, "Waist Fits 127"),
//		FITS_128(128, "Waist Fits 128"),
//		FITS_129(129, "Waist Fits 129"),
//		FITS_130(130, "Waist Fits 130"),
//		FITS_131(131, "Waist Fits 131"),
//		FITS_132(132, "Waist Fits 132"),
//		FITS_133(133, "Waist Fits 133"),
//		FITS_134(134, "Waist Fits 134"),
//		FITS_135(135, "Waist Fits 135"),
//		FITS_136(136, "Waist Fits 136"),
//		FITS_137(137, "Waist Fits 137"),
//		FITS_138(138, "Waist Fits 138"),
//		FITS_139(139, "Waist Fits 139"),
//		FITS_140(140, "Waist Fits 140"),
//		FITS_141(141, "Waist Fits 141"),
//		FITS_142(142, "Waist Fits 142"),
//		FITS_143(143, "Waist Fits 143"),
//		FITS_144(144, "Waist Fits 144"),
//		FITS_145(145, "Waist Fits 145"),
//		FITS_146(146, "Waist Fits 146"),
//		FITS_147(147, "Waist Fits 147"),
//		FITS_148(148, "Waist Fits 148"),
//		FITS_149(149, "Waist Fits 149"),
//		FITS_150(150, "Waist Fits 150"),
//		FITS_151(151, "Waist Fits 151"),
//		FITS_152(152, "Waist Fits 152"),
//		FITS_153(153, "Waist Fits 153"),
//		FITS_154(154, "Waist Fits 154"),
//		FITS_155(155, "Waist Fits 155"),
//		FITS_156(156, "Waist Fits 156"),
//		FITS_157(157, "Waist Fits 157"),
//		FITS_158(158, "Waist Fits 158"),
//		FITS_159(159, "Waist Fits 159"),
//		FITS_160(160, "Waist Fits 160"),
//		FITS_161(161, "Waist Fits 161"),
//		FITS_162(162, "Waist Fits 162"),
//		FITS_163(163, "Waist Fits 163"),
//		FITS_164(164, "Waist Fits 164"),
//		FITS_165(165, "Waist Fits 165"),
//		FITS_166(166, "Waist Fits 166"),
//		FITS_167(167, "Waist Fits 167"),
//		FITS_168(168, "Waist Fits 168"),
//		FITS_169(169, "Waist Fits 169");
		
		FITS_24(11, "Waist Fits 24"),
		FITS_25(12, "Waist Fits 25"),
		FITS_26(13, "Waist Fits 26"),
		FITS_27(14, "Waist Fits 27"),
		FITS_28(15, "Waist Fits 28"),
		FITS_29(16, "Waist Fits 29"),
		FITS_30(17, "Waist Fits 30"),
		FITS_31(18, "Waist Fits 31"),
		FITS_32(19, "Waist Fits 32"),
		FITS_33(20, "Waist Fits 33"),
		FITS_34(21, "Waist Fits 34");
		
		private int index;
		private String desc;
		private Waist(int index, String desc) {
			this.index = index;
			this.desc = desc;
			addFeature(this);
		}
		
		public int index() {
			return index;
		}
		
		public String description() {
			return desc;
		}
	}
	
	//TODO 根据图像特征值的范围修改
	public static enum Hip implements FeatureEnum {
//		FITS_170(170, "Hip Fits 170"),
//		FITS_171(171, "Hip Fits 171"),
//		FITS_172(172, "Hip Fits 172"),
//		FITS_173(173, "Hip Fits 173"),
//		FITS_174(174, "Hip Fits 174"),
//		FITS_175(175, "Hip Fits 175"),
//		FITS_176(176, "Hip Fits 176"),
//		FITS_177(177, "Hip Fits 177"),
//		FITS_178(178, "Hip Fits 178"),
//		FITS_179(179, "Hip Fits 179"),
//		FITS_180(180, "Hip Fits 180"),
//		FITS_181(181, "Hip Fits 181"),
//		FITS_182(182, "Hip Fits 182"),
//		FITS_183(183, "Hip Fits 183"),
//		FITS_184(184, "Hip Fits 184"),
//		FITS_185(185, "Hip Fits 185"),
//		FITS_186(186, "Hip Fits 186"),
//		FITS_187(187, "Hip Fits 187"),
//		FITS_188(188, "Hip Fits 188"),
//		FITS_189(189, "Hip Fits 189"),
//		FITS_190(190, "Hip Fits 190"),
//		FITS_191(191, "Hip Fits 191"),
//		FITS_192(192, "Hip Fits 192"),
//		FITS_193(193, "Hip Fits 193"),
//		FITS_194(194, "Hip Fits 194"),
//		FITS_195(195, "Hip Fits 195"),
//		FITS_196(196, "Hip Fits 196"),
//		FITS_197(197, "Hip Fits 197"),
//		FITS_198(198, "Hip Fits 198"),
//		FITS_199(199, "Hip Fits 199"),
//		FITS_200(200, "Hip Fits 200"),
//		FITS_201(201, "Hip Fits 201"),
//		FITS_202(202, "Hip Fits 202"),
//		FITS_203(203, "Hip Fits 203"),
//		FITS_204(204, "Hip Fits 204"),
//		FITS_205(205, "Hip Fits 205"),
//		FITS_206(206, "Hip Fits 206"),
//		FITS_207(207, "Hip Fits 207"),
//		FITS_208(208, "Hip Fits 208"),
//		FITS_209(209, "Hip Fits 209"),
//		FITS_210(210, "Hip Fits 210"),
//		FITS_211(211, "Hip Fits 211"),
//		FITS_212(212, "Hip Fits 212"),
//		FITS_213(213, "Hip Fits 213"),
//		FITS_214(214, "Hip Fits 214"),
//		FITS_215(215, "Hip Fits 215"),
//		FITS_216(216, "Hip Fits 216"),
//		FITS_217(217, "Hip Fits 217"),
//		FITS_218(218, "Hip Fits 218"),
//		FITS_219(219, "Hip Fits 219"),
//		FITS_220(220, "Hip Fits 220"),
//		FITS_221(221, "Hip Fits 221"),
//		FITS_222(222, "Hip Fits 222"),
//		FITS_223(223, "Hip Fits 223"),
//		FITS_224(224, "Hip Fits 224"),
//		FITS_225(225, "Hip Fits 225"),
//		FITS_226(226, "Hip Fits 226"),
//		FITS_227(227, "Hip Fits 227"),
//		FITS_228(228, "Hip Fits 228"),
//		FITS_229(229, "Hip Fits 229"),
//		FITS_230(230, "Hip Fits 230"),
//		FITS_231(231, "Hip Fits 231"),
//		FITS_232(232, "Hip Fits 232"),
//		FITS_233(233, "Hip Fits 233"),
//		FITS_234(234, "Hip Fits 234"),
//		FITS_235(235, "Hip Fits 235"),
//		FITS_236(236, "Hip Fits 236"),
//		FITS_237(237, "Hip Fits 237"),
//		FITS_238(238, "Hip Fits 238"),
//		FITS_239(239, "Hip Fits 239"),
//		FITS_240(240, "Hip Fits 240"),
//		FITS_241(241, "Hip Fits 241"),
//		FITS_242(242, "Hip Fits 242"),
//		FITS_243(243, "Hip Fits 243"),
//		FITS_244(244, "Hip Fits 244"),
//		FITS_245(245, "Hip Fits 245"),
//		FITS_246(246, "Hip Fits 246"),
//		FITS_247(247, "Hip Fits 247"),
//		FITS_248(248, "Hip Fits 248"),
//		FITS_249(249, "Hip Fits 249"),
//		FITS_250(250, "Hip Fits 250"),
//		FITS_251(251, "Hip Fits 251"),
//		FITS_252(252, "Hip Fits 252"),
//		FITS_253(253, "Hip Fits 253");
		
		FITS_24(22, "Hip Fits 24"),
		FITS_25(23, "Hip Fits 25"),
		FITS_26(24, "Hip Fits 26"),
		FITS_27(25, "Hip Fits 27"),
		FITS_28(26, "Hip Fits 28"),
		FITS_29(27, "Hip Fits 29"),
		FITS_30(28, "Hip Fits 30"),
		FITS_31(29, "Hip Fits 31"),
		FITS_32(30, "Hip Fits 32"),
		FITS_33(31, "Hip Fits 33"),
		FITS_34(32, "Hip Fits 34");
		
		private int index;
		private String desc;
		private Hip(int index, String desc) {
			this.index = index;
			this.desc = desc;
			addFeature(this);
		}
		
		public int index() {
			return index;
		}
		
		public String description() {
			return desc;
		}
	}
	
	
	//TODO 根据图像特征值的范围修改
	public static enum Length implements FeatureEnum {

		
		FITS_R(33, "Length Regular"),
		FITS_L(34, "Length Long");
		
//		FITS_R(254, "Length Regular"),
//		FITS_L(255, "Length Long");
		
		private int index;
		private String desc;
		private Length(int index, String desc) {
			this.index = index;
			this.desc = desc;
			addFeature(this);
		}
		
		public int index() {
			return index;
		}
		
		public String description() {
			return desc;
		}
	}
}
