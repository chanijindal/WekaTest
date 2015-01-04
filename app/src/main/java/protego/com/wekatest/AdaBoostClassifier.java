package protego.com.wekatest;


class AdaBoostClassifier {

    public static double classify(Object[] i) {
        double [] sums = new double [2];
        sums[(int) AdaBoostClassifier_0.classify(i)] += 0.587786664902119;
        sums[(int) AdaBoostClassifier_1.classify(i)] += 0.9555114450274369;
        sums[(int) AdaBoostClassifier_2.classify(i)] += 1.1192315758708453;
        sums[(int) AdaBoostClassifier_3.classify(i)] += 1.3298530502149393;
        sums[(int) AdaBoostClassifier_4.classify(i)] += 0.8656063309311587;
        sums[(int) AdaBoostClassifier_5.classify(i)] += 0.5106355925779476;
        sums[(int) AdaBoostClassifier_6.classify(i)] += 0.7238929253170712;
        sums[(int) AdaBoostClassifier_7.classify(i)] += 0.9580042765825909;
        sums[(int) AdaBoostClassifier_8.classify(i)] += 0.7249658665972768;
        sums[(int) AdaBoostClassifier_9.classify(i)] += 0.7188537851224541;
        double maxV = sums[0];
        int maxI = 0;
        for (int j = 1; j < 2; j++) {
            if (sums[j] > maxV) { maxV = sums[j]; maxI = j; }
        }
        return (double) maxI;
    }
}
class AdaBoostClassifier_0 {
    public static double classify(Object[] i) {
    /* outlook */
        if (i[0] == null) { return 0; } else if (((String)i[0]).equals("overcast")) { return 0; } else { return 0; }
    }
}
class AdaBoostClassifier_1 {
    public static double classify(Object[] i) {
    /* outlook */
        if (i[0] == null) { return 0; } else if (((String)i[0]).equals("overcast")) { return 0; } else { return 1; }
    }
}
class AdaBoostClassifier_2 {
    public static double classify(Object[] i) {
    /* humidity */
        if (i[2] == null) { return 0; } else if (((Double)i[2]).doubleValue() <= 82.5) { return 0; } else { return 1; }
    }
}
class AdaBoostClassifier_3 {
    public static double classify(Object[] i) {
    /* temperature */
        if (i[1] == null) { return 0; } else if (((Double)i[1]).doubleValue() <= 66.5) { return 1; } else { return 0; }
    }
}
class AdaBoostClassifier_4 {
    public static double classify(Object[] i) {
    /* outlook */
        if (i[0] == null) { return 1; } else if (((String)i[0]).equals("overcast")) { return 0; } else { return 1; }
    }
}
class AdaBoostClassifier_5 {
    public static double classify(Object[] i) {
    /* humidity */
        if (i[2] == null) { return 0; } else if (((Double)i[2]).doubleValue() <= 95.5) { return 0; } else { return 0; }
    }
}
class AdaBoostClassifier_6 {
    public static double classify(Object[] i) {
    /* humidity */
        if (i[2] == null) { return 1; } else if (((Double)i[2]).doubleValue() <= 95.5) { return 1; } else { return 0; }
    }
}
class AdaBoostClassifier_7 {
    public static double classify(Object[] i) {
    /* humidity */
        if (i[2] == null) { return 0; } else if (((Double)i[2]).doubleValue() <= 82.5) { return 0; } else { return 1; }
    }
}
class AdaBoostClassifier_8 {
    public static double classify(Object[] i) {
    /* humidity */
        if (i[2] == null) { return 0; } else if (((Double)i[2]).doubleValue() <= 95.5) { return 0; } else { return 0; }
    }
}
class AdaBoostClassifier_9 {
    public static double classify(Object[] i) {
    /* humidity */
        if (i[2] == null) { return 0; } else if (((Double)i[2]).doubleValue() <= 95.5) { return 1; } else { return 0; }
    }
}