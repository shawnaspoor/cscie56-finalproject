<div class="serviceOrderList">
    <div class="serviceOrderDesc">
        Home:  ${serviceOrder.homes.propertyTitle}
        <br/>
        Location: ${serviceOrder.location}
        <br/>
        Description: ${serviceOrder.description}
        <br/>
        Completed?  ${serviceOrder.completed}
        <br/></div>
    <div class="serviceOrderDate">
        Created: <pc:dateFrom date="${serviceOrder.dateCreated}"/>
    </div>
     <button><a href="/propertyconnection/serviceOrder/edit/${serviceOrder.id}">Edit</a></button
    <hr />
</div>