<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />
      
      <div class="row">
        <div class="col-lg-12">
          <h2 class="page-header"> View Customer Account</h2>
        </div>
      </div>
      <div class="col-lg-6">
        <form action="viewCustomerAccount.html">
          <div class="form-group">
          <label>Choose an account by username:</label>
          <select required class="form-control">
            <option></option>
            <option>username1</option>
            <option>username2</option>
            <option>username3</option>
            <option>username4</option>
          </select>
          <br>
          <button type="submit" class="btn btn-primary">Select Account</button>
        </form>
      </div>
    </div>
  </div>
</div>

<jsp:include page="template-buttom.jsp" />