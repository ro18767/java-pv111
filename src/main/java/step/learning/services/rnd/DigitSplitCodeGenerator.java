package step.learning.services.rnd;

import com.google.inject.Inject;

import java.util.Random;

public class DigitSplitCodeGenerator implements CodeGenerator {
    private final Random random;

    @Inject
    public DigitSplitCodeGenerator(Random random) {
        this.random = random;
    }

    @Override
    public String newCode(int length) {
        char[] chars = new char[length + Math.max(length / 4 - 1, 0)];

        for (int i = 0; i < length; i++) {
            chars[i + i / 4] = (char) ((int) '0' + random.nextInt(10));
        }

        for (int i = 4; i < length; i += 5) {
            chars[i] = '-';
        }
        return new String(chars);
    }
}
