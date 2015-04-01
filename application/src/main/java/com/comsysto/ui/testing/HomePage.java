package com.comsysto.ui.testing;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {

	private int slowmotion;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		slowmotion = parameters.get("slowmotion").toInt(0);

		slowmotion();

		IModel<TimeSaving> timeSavingModel = Model.of(new TimeSaving(5,1000));

		setDefaultModel(timeSavingModel);

		final Label resultInSecondsPerDay = new Label("resultInSecondsPerDay", new PropertyModel<>(getDefaultModel(), "resultInSecondsPerDay"));
		final Label resultInSecondsPerYear = new Label("resultInSecondsPerYear", new PropertyModel<>(getDefaultModel(), "resultInSecondsPerYear"));
		final WebMarkupContainer results = new WebMarkupContainer("results");
		results.setVisible(false);
		add(results);
		results.add(resultInSecondsPerDay);
		results.add(resultInSecondsPerYear);

		Form form = new Form("form") {
			@Override
			protected void onSubmit() {
				slowmotion();
				timeSavingModel.getObject().calculateAndSetTimeSaving();
				results.setVisible(true);
			}
		};

		add(form);

		final TextField<Integer> timePerUser = new TextField<>("timePerUser", new PropertyModel<>(getDefaultModel(), "timePerUser"));
		form.add(timePerUser);
		final TextField<Integer> amountUsers = new TextField<>("amountUsers", new PropertyModel<>(getDefaultModel(), "amountUsers"));
		form.add(amountUsers);
		form.add(new SubmitLink("submit"));
    }

	private void slowmotion () {
		if (slowmotion > 0) {
			try {
				Thread.sleep(slowmotion * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
