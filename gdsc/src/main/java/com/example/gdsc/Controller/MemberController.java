package com.example.gdsc.Controller;


import com.example.gdsc.Service.MemberService;
import com.example.gdsc.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index(@ModelAttribute MemberDto memberDto,Model model,HttpSession session){
        List<MemberDto> memberDtoList = memberService.findAll();

        model.addAttribute("memberList", memberDtoList);

        String loginId = (String) session.getAttribute("loginId");
        if (loginId!=null) {
            model.addAttribute("loginId", loginId);
            //session.setAttribute("loginId", loginId);
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() throws Exception{
        return "login";

    }
    @PostMapping("/register")
    public String register(@ModelAttribute MemberDto memberDto, Model model){

        memberService.save(memberDto);
        model.addAttribute("message","회원 가입이 완료되었습니다.");
        model.addAttribute("searchUrl","/");
        return "message";
    }

    @GetMapping("/register")
    public String registerForm(){
        return "register";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session, Model model){
        MemberDto loginResult = memberService.login(memberDto);
        if (loginResult != null){
            session.setAttribute("loginId",loginResult.getId());
            session.setAttribute("Code",loginResult.getCode());
            model.addAttribute("message","로그인에 성공했습니다.");
            model.addAttribute("searchUrl","/");
            return "message";
        }
        else{
            model.addAttribute("message","로그인에 실패했습니다.");
            model.addAttribute("searchUrl","/login");
            return "message";
        }
    }


    @GetMapping("/member/{code}")
    public String findById(@PathVariable Long code, Model model,HttpSession session){

        MemberDto memberDto = memberService.findById(code);
        model.addAttribute("member",memberDto);
        return "info";
    }
    @GetMapping("/logout")
    public String logout(Model model,HttpSession session){
        session.invalidate();
        model.addAttribute("message","로그인에 실패했습니다.");
        model.addAttribute("searchUrl","/login");
        return "message";
    }

    @DeleteMapping("/delete/{code}")
    public String deleteId(@PathVariable Long code, Model model, HttpSession session){
        memberService.deleteById(code);

        session.removeAttribute("Code");
        session.removeAttribute("loginId");
        model.addAttribute("message","계정 삭제가 완료됬습니다.");
        model.addAttribute("searchUrl","/");
        return "message";
    }
    @GetMapping("/update/{code}")
    public String updateForm(@PathVariable Long code, Model model){

        MemberDto memberDto = memberService.updateForm(code);
        model.addAttribute("update",memberDto);
        return "update";
    }
    @PutMapping("/update/{code}")
    public String updateId(@ModelAttribute MemberDto memberDto, Model model){

        memberService.update(memberDto);
        model.addAttribute("message","수정이 완료됬습니다.");
        model.addAttribute("searchUrl","/");
        return "message";
    }
}
