INSERT INTO public.app_user(
	 email, password, person_person_id)
	SELECT 'admin@wp.pl', '{noop}admin', null
	WHERE NOT EXISTS (SELECT  * FROM public.app_user WHERE email='admin@wp.pl');


-- INSERT INTO public.bill(23, 323, 124, null);

