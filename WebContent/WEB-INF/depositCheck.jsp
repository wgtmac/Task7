<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />
      
      <div class="row">
        <div class="col-lg-12">
          <h2 class="page-header"> Deposit Check </h2>
        </div>
      </div>
      <form action="viewCustomerAccount.do">
        <div class="col-lg-6">
          <div class="form-group">
            <label>Choose Customer Account (Username):</label>
            <select required class="form-control">
              <option></option>
              <option>username1</option>
              <option>username2</option>
              <option>username3</option>
              <option>username4</option>
            </select>
            </div>
            <div class="form-group">
              <label>Amount:</label>
              <input name="amount" type="text" required class="form-control" placeholder="Only numbers in the format 1000.00">
            </div>
            <div class="form-group">
              <label>Confirm Amount:</label>
              <input name="confAmount" type="text" class="form-control" name="userName" placeholder="Only numbers in the format 1000.00">
            </div>
            <div class="form-group">
              <button type="submit" name="deposit" class="btn btn-primary">Deposit</button>
            </div>
          </div>
        </form>

      
<jsp:include page="template-buttom.jsp" />