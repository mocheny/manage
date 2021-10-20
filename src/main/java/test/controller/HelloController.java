package test.controller;

import javafx.scene.web.WebEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import test.dao.DorDao;
import test.dao.StuDao;
import test.domain.Dormitory;
import test.domain.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    private StuDao stuDao;

    @Autowired
    private DorDao dorDao;

    //首页
    @RequestMapping(value = "/", produces = "test/html;charset=UTF-8")
    public String index(){
        return "index";
    }

    //学生管理界面
    @RequestMapping(value = "/allStu", method = RequestMethod.GET, produces = "test/html;charset=UTF-8")
    public String allStu(Model model) throws Exception {
        Collection<Student> employeeList = stuDao.queryAll();
        model.addAttribute("employeeList", employeeList);


        return "AllStu";
    }

    //宿舍管理界面
    @RequestMapping(value = "/allDor", method = RequestMethod.GET, produces = "test/html;charset=UTF-8")
    public String allDor(Model model) throws Exception
    {
        Collection<Dormitory> employeeList = dorDao.queryAll();
        model.addAttribute("employeeList", employeeList);
        return "AllDor";
    }

    //学生详情
    @RequestMapping(value = "/stu/{id}", method = RequestMethod.GET, produces = "test/html;charset=UTF-8")
    public String stu(@PathVariable("id") Integer id, Model model) throws Exception {
        Collection<Student> em = stuDao.queryOne(id);
        model.addAttribute("em",em);
        return "Stu";
    }

    //宿舍详情
    @RequestMapping(value = "/dor/{id}", method = RequestMethod.GET, produces = "test/html;charset=UTF-8")
    public String dor(@PathVariable("id") Integer id, Model model) throws Exception {
        Collection<Dormitory> em = dorDao.queryOne(id);
        model.addAttribute("em",em);
        return "Dor";
    }

    //删除学生
    @RequestMapping(value = "/allStu/{id}", method = RequestMethod.DELETE, produces = "test/html;charset=UTF-8")
    public String delStu(@PathVariable("id") Integer id) throws Exception {
        stuDao.delete(id);
        return "redirect:/allStu";
    }

    //修改学生时回显页面
    @RequestMapping(value = "/modStu/{id}", method = RequestMethod.GET, produces = "test/html;charset=UTF-8")
    public String modStu1(@PathVariable("id") Integer id, Model model) throws Exception {
        Student employee = stuDao.queryOne2(id);
        model.addAttribute("employee",employee);
        return "ModStu";
    }

    //修改学生
    @RequestMapping(value = "/modStu", method = RequestMethod.PUT, produces = "test/html;charset=UTF-8")
    public void modStu2(Student student, HttpServletResponse response) throws Exception {

     //   return "redirect:/allStu";



        int c = stuDao.full(student);
        int d = stuDao.sex(student);
        int e = stuDao.exist(student);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //先判断有无该学生，然后有无该寝室，然后寝室满员，最后性别

        //返回路径不对，需要修改  ????
            if (e == 0)
            {
                out.print("<script type  = 'text/javascript'>alert('没有该寝室，请重新修改'); location.href='allStu'</script>");


            }
            else {
                if (c == 0)
                    out.print("<script type  = 'text/javascript'>alert('该寝室已满员，请重新修改'); location.href='allStu'</script>");


                else {
                    if (d == 0)
                        out.print("<script type  = 'text/javascript'>alert('与所添加寝室的性别不相符，请重新修改'); location.href='allStu'</script>");

                    else {
                        stuDao.modify(student);
                        out.print("<script type  = 'text/javascript'>alert('修改成功');  location.href = 'allStu'</script>");
                    }
                }
            }

        out.flush();
        out.close();
    }

    /*
    //修改宿舍时回显
    @RequestMapping(value = "/modDor/{id}", method = RequestMethod.GET)
    public String modDor1(@PathVariable("id") Integer id, Model model) throws Exception {
        Dormitory employee = dorDao.queryOne2(id);
        model.addAttribute("employee",employee);
        return "ModDor";
    }

    //修改宿舍
    @RequestMapping(value = "/modDor", method = RequestMethod.PUT)
    public String modDor2(Dormitory dormitory) throws Exception {
        dorDao.modify(dormitory);
        return "redirect:/allDor";
    }


     */

    //添加学生转换界面
    @RequestMapping(value = "/addStu", produces = "test/html;charset=UTF-8")
    public String addStu(){
        return "AddStu";
    }

    //添加学生操作
    @RequestMapping(value = "/addStu2", method = RequestMethod.POST, produces = "test/html;charset=UTF-8")
    public void addStu2(Student student, HttpServletResponse response) throws Exception {
   //     stuDao.insert(student);
        int b = stuDao.count(student);
        int c = stuDao.full(student);
        int d = stuDao.sex(student);
        int e = stuDao.exist(student);

       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //先判断有无该学生，然后有无该寝室，然后寝室满员，最后性别
        if (b == 0) {//学生
            if (e == 0)
            {
                out.print("<script type  = 'text/javascript'>alert('没有该寝室，请重新添加'); location.href='addStu'</script>");


            }
            else {
                if (c == 0)
                    out.print("<script type  = 'text/javascript'>alert('该寝室已满员，请重新添加'); location.href='addStu'</script>");


                else {
                    if (d == 0)
                        out.print("<script type  = 'text/javascript'>alert('与所添加寝室的性别不相符，请重新添加'); location.href='addStu'</script>");

                    else {
                        stuDao.insert(student);
                        out.print("<script type  = 'text/javascript'>alert('添加新学生成功');  location.href = 'allStu'</script>");
                    }
                }
            }
        } else
        {
            out.print("<script type  = 'text/javascript'>alert('已有该学生，请重新添加'); location.href='addStu'</script>");
        }
        out.flush();
        out.close();
    }
}
