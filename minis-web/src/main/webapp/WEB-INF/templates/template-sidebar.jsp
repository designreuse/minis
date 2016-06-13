<%@ page language="java" pageEncoding="UTF-8" %>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
      <li class="header">EXPLORE</li>
      <li class="treeview">
        <a href="#">
          <i class="ti ti-settings"></i><span>人員角色管理</span>
          <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${contextPath}/aa/user.html"><i class="fa fa-circle-o"></i>人員管理</a></li>
          <li><a href="${contextPath}/aa/role.html"><i class="fa fa-circle-o"></i>角色管理</a></li>
          <li><a href="${contextPath}/aa/role-group.html"><i class="fa fa-circle-o"></i>角色群組管理</a></li>
        </ul>
      </li>
      <li class="treeview">
        <a href="#">
          <i class="ti ti-settings"></i><span>應用程式授權管理</span>
          <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${contextPath}/aa/application.html"><i class="fa fa-circle-o"></i>應用程式管理</a></li>
          <li><a href="${contextPath}/aa/controlitem.html"><i class="fa fa-circle-o"></i>控制元件管理</a></li>
          <li><a href="${contextPath}/aa/application-permission.html"><i class="fa fa-circle-o"></i>應用程式授權管理</a></li>
          <li><a href="${contextPath}/aa/controlitem-permission.html"><i class="fa fa-circle-o"></i>控制元件授權管理</a></li>
        </ul>
      </li>
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>

<script>

  $(function() {
      // Active Current Links.
      var activeLink = $(".sidebar-menu").find("a[href='" + window.location.pathname + "']");
      if(activeLink !== undefined) {
          activeLink.parent("li").addClass("active");
          activeLink.closest(".treeview").addClass("active");
      }
  });

</script>
