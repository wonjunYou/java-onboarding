package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {
    public static final int MAX_NUMBER_OF_PEOPLE_CAN_RECOMMEND = 5;
    private static final Map<String, Set<String>> friendList = new HashMap<>();
    private static final Map<String, Integer> recommendScore = new HashMap<>();
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();

        initAllFriendList(friends);
        initAllRecommendScore(user);

        calculateRecommendScore(visitors);

        return answer;
    }

    private static void initAllFriendList(List<List<String>> friends) {
        for (List<String> friend : friends) {
            addFriendEachOther(friend);
        }
    }

    private static void addFriendEachOther(List<String> friend) {
        addFriend(friend.get(0), friend.get(1));
        addFriend(friend.get(1), friend.get(0));
    }

    private static void addFriend(String key, String value) {
        if (friendList.containsKey(key)) {
            friendList.get(key).add(value);
            return;
        }

        Set<String> friendListForEachUser = new HashSet<>();
        friendListForEachUser.add(value);
        friendList.put(key, friendListForEachUser);
    }

    private static void initAllRecommendScore(String user) {
        for (String targetUser : friendList.keySet()) {
            initRecommendScore(user, targetUser);
        }
    }

    private static void initRecommendScore(String user, String targetUser) {
        Set<String> userFriends = friendList.get(user);
        Set<String> targetUserFriends = friendList.get(targetUser);

        recommendScore.put(targetUser, countFriendUserKnowTogether(userFriends, targetUserFriends) * 10);
    }

    private static int countFriendUserKnowTogether(Set<String> userFriends, Set<String> targetUserFriends) {
        return (int) targetUserFriends.stream()
                .filter(userFriends::contains)
                .count();
    }

    private static void calculateRecommendScore(List<String> visitors) {
        addScoreOfVisitors(visitors);
    }

    private static void addScoreOfVisitors(List<String> visitors) {
        for (String visitor : visitors) {
            addScoreOfVisitor(visitor);
        }
    }

    private static void addScoreOfVisitor(String visitor) {
        if (recommendScore.containsKey(visitor)) {
            recommendScore.put(visitor, recommendScore.get(visitor) + 1);
            return;
        }

        recommendScore.put(visitor, 1);
    }
}
