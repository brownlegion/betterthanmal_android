package com.example.owner.betterthanmal;

/**
 * Created by Krishna N. Deoram on 2016-08-04.
 * Gumi is love. Gumi is life.
 */
public interface Constants {
    
    String ip = "http://159.203.13.218";
    String hash = "1d951f1953958f15a71e2978e37267ebaed1a06d";
    String signinPath = "/back_end/signin.php";
    String registerPath = "/back_end/registerUser.php";
    String queryPath = "/back_end/queryWaifuDatabase.php";
    String insertPath = "/back_end/insertWaifuDatabase.php";

    String[] fragmentTags = {
            "fragmentWaifu", // 0
            "fragmentTitle", // 1
            "fragmentSeiyuu", //2
            "fragmentSeiyuuTitle", //3
            "fragmentSeiyuuArchetype", //4
            "fragmentWebview", // 5
            "fragmentSignin", //6
            "fragmentRegister" //7
    };
}
