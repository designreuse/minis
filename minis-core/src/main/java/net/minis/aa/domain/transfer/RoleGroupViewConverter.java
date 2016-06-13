package net.minis.aa.domain.transfer;
//package net.minis.core.domain.transfer;
//
//import javax.annotation.Resource;
//
//import net.minis.api.convert.ModelConverter;
//import net.minis.core.domain.User;
//import net.minis.core.service.RoleService;
//import net.minis.core.service.UserService;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class RoleGroupViewConverter extends ModelConverter {
//
//    @Resource
//    private UserService userService;
//
//    @Resource
//    private RoleService roleService;
//
//    public RoleGroupView convert(User source) {
//
//        if (source == null) {
//            return null;
//        }
//
//        RoleGroupView target = new RoleGroupView();
////        target.setUser(convertUserView(source));
////        target.setRoles(convertRoleView(source));
//
//        return target;
//    }
//
//    @Override
//    public Object convert(Object source, Object target) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
////    private User convertUserView(User source) {
////        return userViewConverter.convert(source);
////    }
////
////    private List<RoleView> convertRoleView(User source) {
////        String username = source.getUsername();
////        List<Role> roles = roleService.findRolesByUsername(username);
////        return roleViewConverter.convert(roles);
////    }
//
//}
