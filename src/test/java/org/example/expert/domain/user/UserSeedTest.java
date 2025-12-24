package org.example.expert.domain.user;

import org.example.expert.domain.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class UserSeedTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String[] adjectives ={
            "Happy", "Sad", "Angry", "Brave", "Calm", "Clever", "Crazy", "Dark", "Bright",
            "Fierce", "Gentle", "Bold", "Lucky", "Shy", "Wild", "Tiny", "Huge", "Fast",
            "Slow", "Quiet", "Loud", "Smart", "Funny", "Silly", "Strong", "Weak", "Hot",
            "Cold", "Soft", "Hard", "Sweet", "Bitter", "Rich", "Poor", "Young", "Old",
            "Friendly", "Mean", "Lazy", "Active", "Cheerful", "Gloomy", "Curious", "Mysterious",
            "Elegant", "Fancy", "Plain", "Simple", "Sharp", "Dull", "Brightest", "Darkest",
            "Courageous", "Timid", "Wise", "Foolish", "Glorious", "Tinyest", "Massive", "Quick",
            "Slowest", "Delightful", "Horrible", "Joyful", "Miserable", "Peaceful", "Anguished",
            "Cleverest", "Silliest", "Strongest", "Weakest", "Gentlest", "Fiercest", "Bravest",
            "Calmest", "Laziest", "Happiest", "Saddest", "Richest", "Poorest", "Youngest",
            "Oldest", "Friendliest", "Meanest", "Brightest", "Darkest", "Sharpest", "Dullest",
            "Sweetest", "Bitterest", "Fastest", "Slowest", "Cheeriest", "Gloomiest", "Curiousest",
            "Mysteriousest", "Eleganter", "Fancier", "Simplest", "Plainest", "Courageousest",
            "Timidest", "Wisest", "Foolishest", "Gloriouser", "Massiver", "Quickest", "Delightfullest",
            "Horriblest", "Joyfullest", "Miserablest", "Peacefullest", "Anguishedest", "Brightly",
            "Darkly", "Softly", "Hardly", "Sweetly", "Bitterly", "Happily", "Sadly", "Bravely",
            "Calmly", "Boldly", "Fiercely", "Gently", "Cleverly", "Lazily", "Quickly", "Slowly",
            "Silently", "Loudly", "Friendly", "Meanly", "Curiously", "Mysteriously", "Elegantly",
            "Fancily", "Simply", "Sharply", "Dully", "Richly", "Poorly", "Youngly", "Oldly",
            "Cheerfully", "Gloomily", "Angrily", "Joyfully", "Miserably", "Peacefully", "Timidly",
            "Wisely", "Foolishly", "Gloriously", "Delightfully", "Horribly", "Massively", "Boldest",
            "Brightest", "Darkest", "Calmest", "Laziest", "Fastest", "Slowest", "Softest", "Hardest",
            "Sweetest", "Bitterest", "Friendliest", "Meanest", "Curiousest", "Mysteriousest",
            "Eleganter", "Fancier", "Simplest", "Plainest", "Courageousest", "Timidest", "Wisest",
            "Foolishest", "Gloriouser", "Massiver", "Quickest", "Delightfullest", "Horriblest",
            "Joyfullest", "Miserablest", "Peacefullest", "Anguishedest", "Brightly", "Darkly",
            "Softly", "Hardly", "Sweetly", "Bitterly", "Happily", "Sadly", "Bravely", "Calmly"
    };

    private final String[] nouns = {
            "Tiger", "Lion", "Bear", "Wolf", "Fox", "Eagle", "Hawk", "Shark", "Rabbit", "Dragon",
            "Owl", "Monkey", "Horse", "Squirrel", "Butterfly", "Panther", "Falcon", "Otter",
            "Koala", "Elephant", "Crow", "Snake", "Dolphin", "Llama", "Penguin", "Frog", "Seal",
            "Cheetah", "Deer", "Gorilla", "Hummingbird", "Turtle", "Tigeress", "Bearcub",
            "Wolfpack", "Foxcub", "Lioness", "Eaglet", "Hawkling", "Sharky", "Bunny", "Dragonet",
            "Owlet", "MonkeyKing", "Horsey", "Squirrelly", "Butter", "Panthy", "Falconer", "Ottery",
            "KoalaBear", "Elephantine", "Crowley", "Snakebite", "Dolphy", "Llamazing", "Pengy",
            "Froggy", "Sealy", "CheetahKing", "Deery", "Gorilly", "Hummy", "Turtley", "TigerKing",
            "BearKing", "Wolfie", "Foxy", "LionKing", "EagleEye", "Hawky", "SharkyKing", "RabbitKing",
            "DragonKing", "OwlKing", "MonkeyKing", "HorseKing", "SquirrelKing", "ButterflyKing",
            "PantherKing", "FalconKing", "OtterKing", "KoalaKing", "ElephantKing", "CrowKing",
            "SnakeKing", "DolphinKing", "LlamaKing", "PenguinKing", "FrogKing", "SealKing",
            "CheetahKing", "DeerKing", "GorillaKing", "HummingbirdKing", "TurtleKing",
            "TigerPrince", "BearPrince", "WolfPrince", "FoxPrince", "LionPrince", "EaglePrince",
            "HawkPrince", "SharkPrince", "RabbitPrince", "DragonPrince", "OwlPrince", "MonkeyPrince",
            "HorsePrince", "SquirrelPrince", "ButterflyPrince", "PantherPrince", "FalconPrince",
            "OtterPrince", "KoalaPrince", "ElephantPrince", "CrowPrince", "SnakePrince",
            "DolphinPrince", "LlamaPrince", "PenguinPrince", "FrogPrince", "SealPrince",
            "CheetahPrince", "DeerPrince", "GorillaPrince", "HummingbirdPrince", "TurtlePrince",
            "TigerQueen", "BearQueen", "WolfQueen", "FoxQueen", "LionQueen", "EagleQueen",
            "HawkQueen", "SharkQueen", "RabbitQueen", "DragonQueen", "OwlQueen", "MonkeyQueen",
            "HorseQueen", "SquirrelQueen", "ButterflyQueen", "PantherQueen", "FalconQueen",
            "OtterQueen", "KoalaQueen", "ElephantQueen", "CrowQueen", "SnakeQueen", "DolphinQueen",
            "LlamaQueen", "PenguinQueen", "FrogQueen", "SealQueen", "CheetahQueen", "DeerQueen",
            "GorillaQueen", "HummingbirdQueen", "TurtleQueen", "TigerKnight", "BearKnight",
            "WolfKnight", "FoxKnight", "LionKnight", "EagleKnight", "HawkKnight", "SharkKnight",
            "RabbitKnight", "DragonKnight", "OwlKnight", "MonkeyKnight", "HorseKnight", "SquirrelKnight",
            "ButterflyKnight", "PantherKnight", "FalconKnight", "OtterKnight", "KoalaKnight",
            "ElephantKnight", "CrowKnight", "SnakeKnight", "DolphinKnight", "LlamaKnight",
            "PenguinKnight", "FrogKnight", "SealKnight", "CheetahKnight", "DeerKnight",
            "GorillaKnight", "HummingbirdKnight", "TurtleKnight"
    };

    @Test
    @Commit
    void insertUniqueRandomUsers() {
        final int totalUsers = 5_000_000;
        final int batchSize = 10_000;

        Random random = new Random();
        int inserted = 0;

        while (inserted < totalUsers) {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO users (email, password, user_role, nickname, created_at) VALUES ");

            for (int i = 0; i < batchSize && inserted < totalUsers; i++, inserted++) {
                String email = "user" + inserted + "@test.com";
                String password = "password";
                String userRole = UserRole.USER.name();

                String nickname = adjectives[random.nextInt(adjectives.length)]
                        + nouns[random.nextInt(nouns.length)]
                        + inserted;

                sql.append("('")
                        .append(email).append("','")
                        .append(password).append("','")
                        .append(userRole).append("','")
                        .append(nickname).append("',NOW()),");
            }

            sql.setLength(sql.length() - 1);
            jdbcTemplate.execute(sql.toString());

            System.out.println("Inserted " + inserted + " users...");
        }

        System.out.println("Finished inserting " + totalUsers + " users.");
    }
}