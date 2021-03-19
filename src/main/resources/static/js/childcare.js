function getMeetings() {
	$.ajax({
        type: "GET",
        url: '/meeting',
        dataType: 'json',
        success: function (data) {
            if (data) {
            	$('#meetings-content').empty();
                data.forEach(function(meeting) {
                	let requesterName = meeting.requester.firstName + " " + meeting.requester.lastName;
                	let giverName = meeting.giver.firstName + " " + meeting.giver.lastName;
                	let initDate = new Date (meeting.initDate).toLocaleString('es-ES')
                	let hoursRequested = Math.floor(meeting.timeRequested / 60);
                	let minutesRequested = meeting.timeRequested % 60;
                	let timeRequested = (hoursRequested > 0) ? hoursRequested + "h " : "";
                	timeRequested += (minutesRequested > 0) ? minutesRequested + "min" : "";
                    $('#meetings-content').append(
                        `<div class="card card-body">\
                        	<div class="row">
								<div class="col-7">Solicitante: ${requesterName}</div><div class="col-5 text-right">Fecha de inicio: ${initDate}</div>
								<div class="col-7">Cuidador: ${giverName}</div><div class="col-5 text-right">Tiempo solicitado: ${timeRequested}</div>
								<div class="col-12">Observaciones: ${meeting.observations}</div>
							</div>
						</div>`);
                });
            }
        },
        error: function(xhr) {
            alert(xhr.responseText);
        }
    });
}

function getBalance() {
	$.ajax({
        type: "GET",
        url: '/meeting/balance',
        dataType: 'json',
        success: function (data) {
            if (data) {
            	$('#balance-content').empty();
            	$('#balance-content').append('<ul></ul>');
                data.forEach(function(balance) {
                	let carerName = balance.carer.firstName + " " + balance.carer.lastName;
                	let hoursRemaining = Math.trunc(balance.timeRemaining / 60);
                	let minutesRemaining = balance.timeRemaining % 60;
                	let timeRemaining = (hoursRemaining != 0) ? hoursRemaining + "h " : "";
                	timeRemaining += (minutesRemaining != 0) ? Math.abs(minutesRemaining) + "min" : "";
                	if (timeRemaining) {
	                    $('#balance-content ul').append(
	                        `<li>
	                        	${carerName} <span class="${balance.timeRemaining > 0 ? 'positive-balance' : 'negative-balance'}">${timeRemaining}</span>
	                        </li>`
	                    );
                	}
                });
            }
        },
        error: function(xhr) {
            alert(xhr.responseText);
        }
    });
}

function postCarer() {

	let carerModel = {
		"firstName": $("#carer-first-name").val(),
		"lastName": $("#carer-last-name").val()
	}

	$.ajax({
		headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        type: "POST",
        url: '/carer',
        dataType: 'json',
        data: JSON.stringify(carerModel),
        success: function (carer) {
        	let carerName = carer.firstName + ' ' + carer.lastName;
            alert("El cuidador "+ carerName + " se ha creado correctamente.");
        	$("#modalNewCarer").modal("hide");
        },
        error: function(xhr) {
            alert(xhr.responseText);
        	$("#modalNewCarer").modal("hide");
        }
    });
}

$(document).ready( function() {
	getMeetings();
	getBalance();
	
	// Events
	$("#btnNewCarer").on('click', function(){
		$("#modalNewCarer").modal("show");
	});
});