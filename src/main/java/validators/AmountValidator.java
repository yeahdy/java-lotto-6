package validators;

import constants.ErrorCodeConstant;
import constants.GameConstant;

public class AmountValidator {

    /**
     * 1. 공백 입력 시 예외 처리 한다.
     * 2. 숫자 외의 문자를 입력했을 경우 예외 처리 한다.
     * 3. 0 원 입력 시 예외 처리 한다.
     * 4. 정수 범위 이상일 경우 예외 처리 한다.
     * 5. 1,000원 단위로 나누어 떨어지지 않는 경우 예외 처리한다.
     */
    public static boolean isVerifyAmount(String purchasedAmount){
        boolean isVerifyAmount = true;
        try{
            isBlankAmount(purchasedAmount);
            isOnlyNumber(purchasedAmount);

            int amount = Integer.parseInt(purchasedAmount);
            isMinNumber(amount);
            isNotMaxNumber(amount);
            isThousandAmount(amount);
        }catch (IllegalArgumentException iae){
            System.out.println(ErrorCodeConstant.DEFAULT_ERROR + iae.getMessage());
            isVerifyAmount = false;
        }
        return isVerifyAmount;
    }

    private static void isBlankAmount(String purchasedAmount){
        if(purchasedAmount.isBlank()){
            throw new IllegalArgumentException(ErrorCodeConstant.STRING_BLANK_ERROR);
        }
    }

    private static void isOnlyNumber(String purchasedAmount){
        final String NUMBER_REGEX = "[0-9]+";

        if(!purchasedAmount.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException(ErrorCodeConstant.NUMBER_VALIDATION_ERROR);
        }
    }

    private static void isNotMaxNumber(int amount){
        if(amount > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(ErrorCodeConstant.MAX_NUMBER_ERROR);
        }
    }

    private static void isMinNumber(int amount){
        if (amount == GameConstant.MIN_AMOUNT_COUNT) {
            throw new IllegalArgumentException(ErrorCodeConstant.MIN_AMOUNT_ERROR);
        }
    }

    private static void isThousandAmount(int amount){
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(ErrorCodeConstant.THOUSAND_AMOUNT_ERROR);
        }
    }

}
