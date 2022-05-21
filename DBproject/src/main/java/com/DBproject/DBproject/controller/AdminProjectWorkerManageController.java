package com.DBproject.DBproject.controller;
import com.DBproject.DBproject.controller.dto.EditPWorkerForm;
import com.DBproject.DBproject.controller.dto.FindPWorkerForm;
import com.DBproject.DBproject.controller.dto.RegisterPWorkerForm;
import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import com.DBproject.DBproject.exception.DateException;
import com.DBproject.DBproject.exception.NoIdException;
import com.DBproject.DBproject.exception.WorksMorethan3Exception;
import com.DBproject.DBproject.service.ProjectInputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.DBproject.DBproject.Service.EmployeeService;
import com.DBproject.DBproject.Service.ProjectService;

@Controller
@RequiredArgsConstructor
public class AdminProjectWorkerManageController {

    // 자동 의존성 주입 Autowired 필요없음 - @RequiredArgsConstructor 로 처리 가능
    private final ProjectInputService projectInputService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;


    // 프로젝트 투입 등록폼이동
    @GetMapping("/input/project")
    public String assignProject(Model model) {
        model.addAttribute("inputProject", new RegisterPWorkerForm());
        return "/works/projectStartRegister";
    }


    // 프로젝트 투입 등록폼 작성 -> 저장
    @PostMapping("/input/projectForm")
    public String InputProject(@Valid @ModelAttribute("inputProject") RegisterPWorkerForm rpwForm, BindingResult result , Model model) {
        if(result.hasErrors()){
            return "/works/projectStartRegister";
        }
        try {
            //해당 직원이 있는지 확인하기 위함
            Employee findOneEmployee = employeeService.findEmployee(Integer.valueOf(rpwForm.getEmployee_id()));
            //해당 프로젝트가 있는지 확인하기 위함
            List<Project> findOneProject = projectService.findOne(rpwForm.getProject_id());
            List<Works_for> projectNum =  projectInputService.findPEmployeeByIdList(Integer.valueOf(rpwForm.getEmployee_id()));
            //해당직원이 3개의 프로젝트에 참여중일 때
            if(projectNum.size()>=3){
                throw new WorksMorethan3Exception("해당 직원은 이미 3개의 프로젝트에 참여중입니다.");
            }
            else if (findOneProject.isEmpty() || findOneEmployee == null){
                throw new NoIdException("해당 프로젝트 또는 해당 프로젝트 인원이 존재하지 않습니다.");
            }

            // 폼으로 받은 데이터를 works for 테이블로 신규등록하기 위한 메서드(아래에 만든 메서드 참고)
            Works_for works_for = worksForSetting(rpwForm);

            if(works_for.getE_start_d().isAfter(works_for.getProject().getEnd_date())  ){
                throw new DateException("프로젝트 종료일자 보다 직원의 프로젝트 참여 일자가 큽니다.");
            }

            if(works_for.getE_job().equals("NULL")){
                throw new IllegalStateException("직책을 제대로 입력해주세요. ");
            }
            // 저장한 내용 DB 반영 -> .inputProjectSave->
            projectInputService.inputProjectSave(works_for);
            return "redirect:/log/adminPage";
        }catch (WorksMorethan3Exception e){
            model.addAttribute("error",new WorksMorethan3Exception(e.getMessage()));
            return "/works/projectStartRegister";
        }
        catch (NoIdException v){
            model.addAttribute("error2",new NoIdException(v.getMessage()));
            return "/works/projectStartRegister";
        }catch (DateException d){
            model.addAttribute("error3",new DateException(d.getMessage()));
            return "/works/projectStartRegister";
        }catch (IllegalStateException e) {
            model.addAttribute("error4", new IllegalStateException(e.getMessage()));
            return "/works/projectStartRegister";
        }
    }
    // 프로젝트 주입 메서드 -> 추후 수정에서 사용할 것이기때문에 public 처리한것
    public Works_for worksForSetting(RegisterPWorkerForm rpwForm) {
        Works_for works_for = new Works_for();
        works_for.setProject(projectInputService.findProjectById(rpwForm.getProject_id()));
        works_for.setEmployee(projectInputService.findPEmployeeById(Integer.valueOf(rpwForm.getEmployee_id())));
        works_for.setE_end_d((rpwForm.getE_end_d()));
        works_for.setE_start_d(LocalDate.parse(rpwForm.getE_start_d(), DateTimeFormatter.ISO_DATE));
        works_for.setE_job(rpwForm.getE_job());
        return works_for;
    }



    // 프로젝트 투입 수정폼 뿌리기
    @GetMapping("/input/project/edit")
    public String goEditInputProject(Model model) {
        model.addAttribute("findForm",new FindPWorkerForm());
        return "/works/editInputProject";
    }

    // 프로젝트 투입 수정폼 검색
    @GetMapping("/input/project/editForm")
    public String SearchInputProject(@Valid @ModelAttribute("findForm")FindPWorkerForm findPWorkerForm,BindingResult result,Model model) {
        if (result.hasErrors()) {
            return "/works/editInputProject";
        }
        try {
            boolean visible = true;
            List<Works_for> prem = projectInputService.findPrEmById(Integer.valueOf(findPWorkerForm.getEmployee_id()),findPWorkerForm.getProject_id());
            // 둘 중 하나라도 works-for 엔티티에 없을때
            if (prem.isEmpty()) {
                throw new NoIdException("진행중인 프로젝트 또는, 프로젝트에 임하는 사원이 존재하지 않습니다.");
            }
            Works_for projectEmployee = prem.get(0); // works for 해당 프로젝트+사원 매핑 가져오기
            model.addAttribute("project", projectEmployee);
            EditPWorkerForm editPWorkerForm=new EditPWorkerForm();
            editPWorkerForm.setProject_id(projectEmployee.getProject().getProject_id());
            editPWorkerForm.setEmployee_id(projectEmployee.getEmployee().getEmployee_id());
            editPWorkerForm.setE_job(projectEmployee.getE_job());
            editPWorkerForm.setE_end_d(projectEmployee.getE_end_d());
            editPWorkerForm.setE_start_d(projectEmployee.getE_start_d());

            model.addAttribute("projectEditForm", editPWorkerForm);
            model.addAttribute("visibility", visible);
//            model.addAttribute("findPWorker", new RegisterPWorkerForm());
            return "/works/editInputProject";
        } catch (NoIdException e) {
            model.addAttribute("error", new NoIdException(e.getMessage()));
            return "/works/editInputProject";
        }
    }
    // 프로젝트 투입 인원 및 프로젝트 검색후 -> 수정
    @PostMapping("/input/project/edit")
    public String EditInputProject(RegisterPWorkerForm rpwForm,Model model){
        try {
            if (rpwForm.getE_job().equals("NULL")) {
                throw new IllegalStateException("직책을 제대로 입력해주세요. ");
            }
        } catch (IllegalStateException e) {
            FindPWorkerForm a = new FindPWorkerForm();
            a.setProject_id(rpwForm.getProject_id());
            a.setEmployee_id(rpwForm.getEmployee_id());

            model.addAttribute("error5", new IllegalStateException(e.getMessage()));
            model.addAttribute("findForm", a);
            model.addAttribute("projectEditForm", rpwForm);
            model.addAttribute("visibility", true);
            return "/works/editInputProject";

        }
        Works_for editInputProject = worksForSetting(rpwForm);
        projectInputService.inputProjectSave(editInputProject);
        return "redirect:/log/adminPage";

    }
}


