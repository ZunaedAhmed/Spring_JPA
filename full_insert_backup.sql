
--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--

INSERT INTO public.category (category_id, name) VALUES (1, 'BRKFST');
INSERT INTO public.category (category_id, name) VALUES (2, 'Lunch');


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--

INSERT INTO public.customer (customer_id, email, name, phone) VALUES (1, 'zunaed.ahmed2@gmail.com', 'Zunaed Ahmed', '07026508770');
INSERT INTO public.customer (customer_id, email, name, phone) VALUES (2, NULL, 'GUEST', NULL);


--
-- Data for Name: din_table; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--

INSERT INTO public.din_table (table_id, location, seats, status) VALUES (1, 'FRONT', 3, 'AVL');
INSERT INTO public.din_table (table_id, location, seats, status) VALUES (2, 'MID', 6, 'AVL');


--
-- Data for Name: inventory_item; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--



--
-- Data for Name: menu_item; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--

INSERT INTO public.menu_item (menu_item_id, name, price, status, category_id) VALUES (1, 'menu1', 1000.00, 'AVL', 1);
INSERT INTO public.menu_item (menu_item_id, name, price, status, category_id) VALUES (2, 'enu2', 800.00, 'AVL', 2);


--
-- Data for Name: menu_item_inventory; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--



--
-- Data for Name: order_item; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--

INSERT INTO public.order_item (order_item_id, line_total, quantity, unit_price, menu_item_id, order_id) VALUES (1, 1000.00, 1, 1000.00, 1, 1);
INSERT INTO public.order_item (order_item_id, line_total, quantity, unit_price, menu_item_id, order_id) VALUES (2, 800.00, 1, 800.00, 2, 2);
INSERT INTO public.order_item (order_item_id, line_total, quantity, unit_price, menu_item_id, order_id) VALUES (3, 1000.00, 1, 1000.00, 1, 4);
INSERT INTO public.order_item (order_item_id, line_total, quantity, unit_price, menu_item_id, order_id) VALUES (4, 800.00, 1, 800.00, 2, 4);


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--

INSERT INTO public.orders (order_id, order_date, order_time, order_type, status, total_amount, customer_id, staff_id, table_id) VALUES (1, '2025-12-29', '17:23:42', 'DINE_IN', 'NEW', 1000.00, 1, NULL, NULL);
INSERT INTO public.orders (order_id, order_date, order_time, order_type, status, total_amount, customer_id, staff_id, table_id) VALUES (2, '2025-12-29', '17:27:10', 'TAKEOUT', 'NEW', 800.00, 1, NULL, 1);
INSERT INTO public.orders (order_id, order_date, order_time, order_type, status, total_amount, customer_id, staff_id, table_id) VALUES (3, '2025-12-29', '18:08:38', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.orders (order_id, order_date, order_time, order_type, status, total_amount, customer_id, staff_id, table_id) VALUES (4, '2025-12-29', '02:00:26', 'DINE_IN', 'NEW', 1800.00, 2, NULL, 1);


--
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--



--
-- Data for Name: staff; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--



--
-- Data for Name: supplier; Type: TABLE DATA; Schema: public; Owner: restaurant_user
--



--
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: restaurant_user
--


