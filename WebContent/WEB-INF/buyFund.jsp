<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />

      <div class="row">
      <div class="col-lg-12">
        <h2 class="page-header"> Buy Funds</h2>
       </div>
       </div>
        <div class="col-lg-6">
        <form>
        <label>Select the fund you want to buy:</label>
        <div class="form-group"> 
          <!-- Add the account stocks below -->
          <select required class="form-control">
            <option selected></option>
            <option>GOOG</option>
            <option>APPL</option>
            <option>AMZN</option>
            <option>ALBB</option>
          </select>
         </div>
      <label>Amount you want to Buy:</label>
      <div class="form-group input-group">
      <span class="input-group-addon">$</span>
      <!-- Add the account amount below -->
      <input type="text" class="form-control" placeholder="Type only numbers in format 1000.00">
      </div>
      <label>Confirm Amount:</label>
      <div class="form-group input-group">
      <span class="input-group-addon">$</span>
      <!-- Add the account amount below -->
      <input type="text" class="form-control" placeholder="Type only numbers in format 1000.00">
      </div>
      <br>
      <button type="submit" class="btn btn-primary">Buy Fund</button>
    </form>
    
<jsp:include page="template-buttom.jsp" />