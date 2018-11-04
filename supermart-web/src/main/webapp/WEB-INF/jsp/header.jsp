<nav class="navbar navbar-expand navbar-dark" style="background-color: indigo">
    <a class="navbar-brand">
        <span class="d-none d-sm-inline">Supermarket</span>
    </a>

    <c:if test="${user.role.ordinal() == 0}">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="inventory-add">Inventory</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="invoice-create">Billing</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="register">Add User</a>
            </li>
        </ul>
    </c:if>
</nav>