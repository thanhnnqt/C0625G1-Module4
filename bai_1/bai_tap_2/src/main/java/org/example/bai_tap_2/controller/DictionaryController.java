package org.example.bai_tap_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "Xin chào");
        dictionary.put("goodbye", "Tạm biệt");
        dictionary.put("thank you", "Cảm ơn");
        dictionary.put("sorry", "Xin lỗi");
        dictionary.put("please", "Làm ơn");
        dictionary.put("yes", "Vâng");
        dictionary.put("no", "Không");
        dictionary.put("morning", "Buổi sáng");
        dictionary.put("night", "Buổi tối");
        dictionary.put("friend", "Bạn bè");
        dictionary.put("family", "Gia đình");
        dictionary.put("love", "Tình yêu");
        dictionary.put("food", "Đồ ăn");
        dictionary.put("drink", "Đồ uống");
        dictionary.put("water", "Nước");
        dictionary.put("coffee", "Cà phê");
        dictionary.put("tea", "Trà");
        dictionary.put("book", "Sách");
        dictionary.put("pen", "Bút");
        dictionary.put("school", "Trường học");
        dictionary.put("teacher", "Giáo viên");
        dictionary.put("student", "Học sinh");
        dictionary.put("computer", "Máy tính");
        dictionary.put("phone", "Điện thoại");
        dictionary.put("car", "Xe hơi");
        dictionary.put("bike", "Xe đạp");
        dictionary.put("house", "Ngôi nhà");
        dictionary.put("city", "Thành phố");
        dictionary.put("country", "Đất nước");
        dictionary.put("work", "Công việc");
        dictionary.put("money", "Tiền");
        dictionary.put("time", "Thời gian");
        dictionary.put("day", "Ngày");
        dictionary.put("night", "Đêm");
        dictionary.put("sun", "Mặt trời");
        dictionary.put("moon", "Mặt trăng");
        dictionary.put("star", "Ngôi sao");
        dictionary.put("flower", "Bông hoa");
        dictionary.put("tree", "Cái cây");
        dictionary.put("rain", "Mưa");
        dictionary.put("wind", "Gió");
        dictionary.put("weather", "Thời tiết");
        dictionary.put("beautiful", "Đẹp");
        dictionary.put("happy", "Hạnh phúc");
        dictionary.put("sad", "Buồn");
        dictionary.put("angry", "Tức giận");
        dictionary.put("hungry", "Đói");
        dictionary.put("tired", "Mệt mỏi");
        dictionary.put("cold", "Lạnh");
        dictionary.put("hot", "Nóng");
        dictionary.put("big", "To");
        dictionary.put("small", "Nhỏ");
        dictionary.put("fast", "Nhanh");
        dictionary.put("slow", "Chậm");
        dictionary.put("new", "Mới");
        dictionary.put("old", "Cũ");
        dictionary.put("beautiful", "Xinh đẹp");
        dictionary.put("ugly", "Xấu xí");
        dictionary.put("strong", "Mạnh mẽ");
        dictionary.put("weak", "Yếu đuối");
        dictionary.put("music", "Âm nhạc");
        dictionary.put("movie", "Bộ phim");
        dictionary.put("game", "Trò chơi");
        dictionary.put("dog", "Con chó");
        dictionary.put("cat", "Con mèo");
        dictionary.put("bird", "Con chim");
        dictionary.put("fish", "Con cá");
    }

    @GetMapping("/home")
    public String showForm(){
        return "home";
    }

    @PostMapping("/search")
    public String search(@RequestParam("word") String word, Model model) {
        String result = dictionary.get(word.toLowerCase());
        if (result != null) {
            model.addAttribute("word", word);
            model.addAttribute("result", result);
        } else {
            model.addAttribute("word", word);
            model.addAttribute("result", "Không tìm thấy từ cần dịch");
        }
        return "result";
    }
}
